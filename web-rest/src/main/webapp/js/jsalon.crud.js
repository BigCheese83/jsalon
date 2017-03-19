"use strict";

(function( $ ) {

    var $dialog = $( "#dialog" ),
        $form = $( "#dialog-form" ),
        $confirm = $( "#confirm" ),
        $confirmMsg = $( "#confirm-msg" ),
        $submit = $( "#submit" ),
        $errTable = $( "#tableErrors" ),
        $errDialog = $( "#dialogErrors" ),
        $createButton = $( "#create-btn" ),
        $editButton = $( "#edit-btn" ),
        $delButton = $( "#delete-btn" ),
        $id = $( "#id" );
    
    var pageName = getLastPathFromUrl();

    var options = {
        datatable: {
            discounts: {
                ajax: {
                    url: "/jsalon/rest/discounts",
                    dataSrc: ""
                },
                columnDefs: [
                    { targets: [ 0, 1, 2 ], className: "dt-center" },
                    { targets: [ 0 ], data: "id", width: "15px", searchable: false },
                    { targets: [ 1 ], data: "name" },
                    { targets: [ 2 ], data: "value" },
                    { targets: [ 3 ], data: "description", defaultContent: "" }
                ]
            },
            posts: {
                ajax: {
                    url: "/jsalon/rest/posts",
                    dataSrc: ""
                },
                columnDefs: [
                    { targets: [ 0, 1, 2 ], className: "dt-center" },
                    { targets: [ 0 ], data: "id", width: "15px", searchable: false },
                    { targets: [ 1 ], data: "name", width: "40%" },
                    { targets: [ 2 ], data: null, width: "110px", searchable: false, orderable: false,
                        defaultContent: "<button class='ui-button ui-corner-all show-btn'>Показать</button>" },
                    { targets: [ 3 ], data: "description", defaultContent: "" }
                ]
            },
            services: {
                ajax: {
                    url: "/jsalon/rest/services",
                    dataSrc: ""
                },
                columnDefs: [
                    { targets: [ 0, 1, 2, 3, 4, 5 ], className: "dt-center" },
                    { targets: [ 0 ], data: "id", width: "15px", searchable: false },
                    { targets: [ 1 ], data: "category" },
                    { targets: [ 2 ], data: "name" },
                    { targets: [ 3 ], data: "cost" },
                    { targets: [ 4 ], data: "duration" },
                    { targets: [ 5 ], data: null, width: "90px", searchable: false, orderable: false,
                        defaultContent: "<button class='ui-button ui-corner-all show-btn'>Показать</button>" },
                    { targets: [ 6 ], data: "description", defaultContent: "" }
                ]
            }
        },
        formMapper: {
            discounts: fillDiscountForm,
            posts: fillPostForm,
            services: fillServiceForm
        },
        formValidator: {}
    };

    var $datatable = $( "#table" ).DataTable( options.datatable[ pageName ] );
    $datatable.on( "select", enableCrudButtons );
    $datatable.on( "deselect", enableCrudButtons );
    $datatable.on( "user-select", function( e, dt, type, cell, originalEvent ) {
        if ( originalEvent.target.nodeName.toLowerCase() === "button" ) {
            e.preventDefault();
        }
    });

    $createButton.button();
    $editButton.button({ disabled: true });
    $delButton.button({ disabled: true });

    $dialog.dialog({
        autoOpen: false,
        modal: true,
        width: "auto",
        resizable: false,
        buttons: {
            "OK": function() {
                $submit.click();
            },
            "Отмена": function() {
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            $form[ 0 ].reset();
            $errDialog.hide();
        }
    });

    $confirm.dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        title: "Удалить",
        buttons: {
            "OK": function() {
                deleteEntity( $( this ).data( "ids" ) );
                $( this ).dialog( "close" );
            },
            "Отмена": function() {
                $( this ).dialog( "close" );
            }
        }
    });

    $form.on( "submit", function( event ) {
        event.preventDefault();
        if ( !validateForm() ) {
            return;
        }
        saveEntity( $id.val() );
        $dialog.dialog( "close" );
    });

    $createButton.on( "click", function() {
        $dialog.dialog( "option", "title", "Создать" );
        $dialog.dialog( "open" );
    });

    $editButton.on( "click", function() {
        var data = $datatable.row({ selected: true }).data(),
            formFill = options.formMapper[ pageName ];
        $id.val( data.id );
        formFill && formFill( data );
        $dialog.dialog( "option", "title", "Редактировать" );
        $dialog.dialog( "open" );
    });

    $delButton.on( "click", function() {
        var ids = $datatable.rows({ selected: true }).ids();
        $confirmMsg.text( "Вы действительно хотите удалить выделенные (" + ids.length + ") строки?" );
        $confirm.data( "ids", ids ).dialog( "open" );
    });

    $( document ).ajaxStart(function() {
        $errTable.hide();
    });

    $( document ).ajaxError(function( event, jqXHR, ajaxSettings, thrownError ) {
        error( $errTable, jsalon.getAjaxError( jqXHR, thrownError ) );
    });

    function saveEntity( id ) {
        var ajaxUrl = options.datatable[ pageName ].ajax.url;
        if ( id ) {
            jsalon.ajaxSendForm( ajaxUrl, "PUT", $form )
                .then(function( data ) {
                    $datatable.row( "#" + id ).data( data ).draw( false );
                });
        } else {
            jsalon.ajaxSendForm( ajaxUrl, "POST", $form )
                .then(function( data ) {
                    $datatable.row.add( data ).draw( false );
                });
        }
    }

    function deleteEntity( ids ) {
        var baseUrl = options.datatable[ pageName ].ajax.url;
        if ( ids.length == 1 ) {
            $.ajax({
                url: baseUrl + "/" + ids[ 0 ],
                type: "DELETE",
                contentType : "application/json"
            }).then(function () {
                $datatable.row( "#" + ids[ 0 ] ).remove().draw( false );
            });
        } else if ( ids.length > 1 ) {
            var query = "?" + ids.map(function( val ) {
                    return "ids=" + val;
                }).join( "&" );
            $.ajax({
                url: baseUrl + query,
                type: "DELETE",
                contentType : "application/json"
            }).then(function () {
                $datatable.rows( ids.map(function( val ) {
                    return "#" + val;
                }) ).remove().draw( false );
            });
        }
    }

    function enableCrudButtons() {
        var selected = $datatable.rows({ selected: true }).count();
        $editButton.button( "option", "disabled", selected === 0 );
        $delButton.button("option", "disabled", selected === 0 );
    }

    function error( $container, text ) {
        $container.show();
        $container.text( text );
    }

    function validateForm() {
        var func = options.formValidator[ pageName ];
        return func ? func() : true;
    }

    function fillDiscountForm( data ) {
        $( "#name" ).val( data.name );
        $( "#value" ).val( data.value );
        $( "#description" ).val( data.description );
    }

    function fillPostForm( data ) {
        $( "#name" ).val( data.name );
        $( "#description" ).val( data.description );
    }

    function fillServiceForm( data ) {
        $( "#category" ).val( data.category );
        $( "#name" ).val( data.name );
        $( "#cost" ).val( data.cost );
        $( "#duration" ).val( data.duration );
        $( "#description" ).val( data.description );
    }

    function getLastPathFromUrl() {
        var url = location.pathname;
        var i = url.lastIndexOf( "/" );
        return url.slice( ++i - url.length );
    }

})( jQuery );
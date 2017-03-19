"use strict";

(function( $ ) {

    var $dialogContent = $( "#dialog-content" ),
        $spinner = $( ".loading"),
        $select = $( "#posts" );
    var posts = [];

    var $datatable = $.fn.dataTable.tables({ visible: true, api: true });

    $( "#table" ).on( "click", ".show-btn", function() {
        var service = $datatable.row( $( this ).parents( "tr" ) ).data();
        $( "#showDialog" ).data( "service", service ).dialog({
            modal: true,
            width: 350,
            minHeight: 200,
            maxWidth: 600,
            maxHeight: 400,
            position: { my: "top", at: "top+90", of: window },
            buttons: {
                "OK": function() {
                    $( this ).dialog( "close" );
                }
            },
            create: function() {
                openShowDialog( $( this ) );
            },
            close: function() {
                $( this ).dialog( "destroy" );
            }
        });
    });

    function openShowDialog( $dialog ) {
        var service = $dialog.data( "service" ),
            inProgress = true;
        $dialogContent.empty();
        $dialog.dialog( "option", "title", "Должности для " + service.name );
        setTimeout(function () {
            inProgress && $spinner.show();
        }, 100 );

        $.ajax({
            url: "/jsalon/rest/posts/services/" + service.id,
            type: "GET",
            contentType : "application/json",
            global: false
        }).done(function( data ) {
            var posts = data || [];
            if ( posts.length > 0 ) {
                var html = posts.reduce(function( str, val ) {
                    return str + "<div class='tag'>" + val.name + "</div>";
                }, "");
                $dialogContent.html( html );
            } else {
                $dialogContent.html( "<p><i class='fa fa-exclamation-triangle fa-2x dialog-icon'></i> " +
                    "Нет должностей, связанных с услугой " + service.name + "</p>");
            }
        }).fail(function( jqXHR, textStatus, errorThrown ) {
            $dialogContent.html( "<div class='err-container' style='display: block'>" +
                jsalon.getAjaxError( jqXHR, errorThrown ) + "</div>" );
        }).always(function () {
            inProgress = false;
            $spinner.hide();
        });
    }

    (function initPostsSelect() {
        $.ajax({
            url: "/jsalon/rest/posts",
            type: "GET",
            contentType : "application/json",
            global: false
        }).then(function ( data ) {
            data = data || [];
            posts = data.map(function( val ) {
                return { id: val.id, text: val.name };
            });
            $select.select2({ data: posts });
        });
    })();

})( jQuery );

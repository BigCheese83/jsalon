"use strict";

(function( $ ) {

    /* Functions */

    function ajaxSendForm( url, method, $form ) {
        return $.ajax({
            url: url,
            type: method,
            data: formToJSON( $form ),
            dataType: "json",
            contentType : "application/json"
        });
    }

    function getAjaxError( jqXHR, errorThrown ) {
        return ( jqXHR.responseJSON && jqXHR.responseJSON.message )
            ? jqXHR.responseJSON.message
            : errorThrown;
    }

    function formToJSON( $form ) {
        var obj = {};
        var params = decodeURIComponent( $form.serialize() ).split( "&" );
        params.forEach(function ( item ) {
            var pair = item.split( "=" );
            if ( pair[ 1 ] ) {
                obj[ pair[ 0 ] ] = pair[ 1 ];
            }
        });
        return JSON.stringify( obj );
    }

    /* Public API */

    window.jsalon = {
        ajaxSendForm: ajaxSendForm,
        getAjaxError: getAjaxError
    };

    /* Internationalization */

    var I18N = {
        RU: {
            dataTable: {
                lengthMenu: "Показать _MENU_ строк",
                zeroRecords: "Ничего не найдено :(",
                info: "Страница _PAGE_ из _PAGES_",
                search: "Поиск:",
                loadingRecords: "Загрузка...",
                processing: "Подождите...",
                infoFiltered: "(отфильтровано из _MAX_ строк)",
                paginate: {
                    first: "Первая",
                    last: "Последняя",
                    next: "Вперед",
                    previous: "Назад"
                },
                select: {
                    rows: {
                        _: "%d строк выделено",
                        0: "",
                        1: "1 строка выделена"
                    }
                }
            }
        }
    };

    /* Initialization */

    (function initUI() {
        var $menu = $( "#menu" );
        $menu.show();
        $menu.menu();
        $( "#content" ).show();
        $( "#floatingCirclesG" ).hide();
    })();

    (function dataTableDefaults() {
        $.extend( true, $.fn.dataTable.defaults, {
            processing: true,
            rowId: "id",
            select: true,
            fixedHeader: {
                headerOffset: $( "#header" ).outerHeight(),
                header: true
            },
            dom: '<"fg-toolbar ui-toolbar ui-widget-header ui-helper-clearfix ui-corner-tl ui-corner-tr"lfBr>' + 't' +
                 '<"fg-toolbar ui-toolbar ui-widget-header ui-helper-clearfix ui-corner-bl ui-corner-br"ip>',
            buttons: [
                {
                    extend:    "excelHtml5",
                    className: "ui-corner-all",
                    text:      "<i class='fa fa-file-excel-o'></i> Excel"
                },
                {
                    extend:    "pdfHtml5",
                    className: "ui-corner-all",
                    text:      "<i class='fa fa-file-pdf-o'></i> PDF"
                },
                {
                    extend:    "print",
                    className: "ui-corner-all",
                    text:      "<i class='fa fa-print'></i> Печать"
                }
            ],
            language: I18N.RU.dataTable
        });

        $.fn.dataTable.ext.buttons.reload = {
            className: "ui-corner-all",
            text: "<i class='fa fa-refresh'></i> Обновить",
            action: function( e, dt, node, config ) {
                dt.ajax.reload( null, false );
            }
        };

        $.fn.dataTable.ext.errMode = function( settings, helpPage, message ) {
            console.error( message );
        };

        $( document ).on( "preInit.dt", function( e, settings ) {
            var api = new $.fn.dataTable.Api( settings );
            new $.fn.dataTable.Buttons( api, {
                buttons: [ "reload" ]
            });
            api.buttons( 1, null ).container().appendTo( $( ".dataTables_length" ) );
        });
    })();

})( jQuery );
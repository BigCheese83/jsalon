"use strict";

(function( $ ) {

    var $table = $( "#table" ),
        $showDialog = $( "#showDialog" ),
        $dialogContent = $( "#dialog-content" ),
        $spinner = $( ".loading");

    var $datatable = $.fn.dataTable.tables({ visible: true, api: true });

    $table.on( "click", ".show-btn", function() {
        var post = $datatable.row( $( this ).parents( "tr" ) ).data();
        $showDialog.data( "post", post ).dialog({
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
        } );
    });

    function openShowDialog( $dialog ) {
        var post = $dialog.data( "post" ),
            inProgress = true;
        $dialogContent.empty();
        $dialog.dialog( "option", "title", "Услуги для " + post.name );
        setTimeout(function () {
            inProgress && $spinner.show();
        }, 100 );
        $.ajax({
            url: "/jsalon/rest/services?postId=" + post.id,
            type: "GET",
            contentType : "application/json",
            global: false
        }).then(function( data ) {
            var services = data || [];
            if ( services.length > 0 ) {
                $dialog.dialog( "option", "width", 600 );
                $dialogContent.html( buildTable( services ) );
            } else {
                $dialogContent.html( "<p><i class='fa fa-exclamation-triangle fa-2x dialog-icon'></i> " +
                    "Нет услуг, связанных с должностью " + post.name + "</p>");
            }
        }, function( jqXHR, textStatus, errorThrown ) {
            $dialogContent.html( "<div class='err-container' style='display: block'>" +
                jsalon.getAjaxError( jqXHR, errorThrown ) + "</div>" );
        }).always(function () {
            inProgress = false;
            $spinner.hide();
        });
    }

    function buildTable( services ) {
        var html = "<table class='showTable'><thead><tr>" +
            "<th>Категория</th><th>Наименование</th><th>Цена</th><th>Время</th><th>Описание</th>" +
            "</tr></thead><tbody>";
        html = services.reduce(function( str, val ) {
            return str + ("<tr><td>" + val.category + "</td><td>" + val.name + "</td><td>" + val.cost +
                "</td><td>" + val.duration + "</td><td>" + (val.description || "") + "</td></tr>" );
        }, html );
        html += "</tbody></table>";
        return html;
    }

})( jQuery );
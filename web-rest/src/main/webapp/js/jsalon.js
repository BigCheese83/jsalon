"use strict";

(function( $ ) {

    var $menu = $( "#menu" );

    $menu.show();
    $menu.menu();
    $( "#content" ).show();
    $( "#floatingCirclesG" ).hide();

    /* Functions */

    function ajaxSendForm( url, method, formSelector ) {
        return $.ajax({
            url: url,
            type: method,
            data: formToJSON( formSelector ),
            dataType: "json",
            contentType : "application/json"
        });
    }

    function formToJSON( formSelector ) {
        var obj = {};
        var params = decodeURIComponent( $( formSelector ).serialize() ).split( "&" );
        params.forEach(function ( item ) {
            var pair = item.split( "=" );
            obj[ pair[ 0 ] ] = pair[ 1 ];
        });
        return JSON.stringify( obj );
    }

    /* Public API */

    window.jsalon = {
        ajaxSendForm: ajaxSendForm
    };

})( jQuery );
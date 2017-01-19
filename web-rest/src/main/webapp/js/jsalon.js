"use strict";

(function( $ ) {

    var $menu = $( "#menu" );

    $menu.show();
    $( "#content" ).show();
    $( "#floatingCirclesG" ).hide();

    $menu.menu();

})( jQuery );
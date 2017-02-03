"use strict";

(function( $ ) {

    var $newPwd = $( "#newPassword" ),
        $confPwd = $( "#confirmpwd" ),
        $pwd = $( "#password" ),
        $msgContainer = $( "#msgContainer" ),
        $msgResult = $( "#msgResult" ),
        $sendButton = $( "#sendButton" );

    $( ".jq-button" ).button();

    $( "#form" ).on( "submit", function ( event ) {
        event.preventDefault();
        clearMessage();
        validateForm() && updateProfile();
    });

    function updateProfile() {
        $sendButton.prop( "disabled", true );
        jsalon.ajaxSendForm( "/jsalon/rest/profile", "PUT", "form" )
            .then(function () {
                showMessage( "Сохранение успешно выполнено", "done" );
                clearForm();
            }, ajaxErrorHandler )
            .always(function () {
                $sendButton.prop( "disabled", false );
            });
    }

    function validateForm() {
        if ( $newPwd.val() != $confPwd.val() ) {
            error( "Введенные пароли не совпадают!" );
            return false;
        }
        return true;
    }

    function clearMessage() {
        $msgContainer.hide();
    }

    function clearForm() {
        $pwd.val("");
    }

    function showMessage( msg, clazz ) {
        $msgContainer.show();
        $msgResult.text( msg );
        $msgResult.attr( "class", clazz );
    }

    function error( msg ) {
        showMessage( msg, "err" );
    }

    function ajaxErrorHandler( jqXHR, textStatus, errorThrown ) {
        var err = ( jqXHR.responseJSON && jqXHR.responseJSON.message )
            ? jqXHR.responseJSON.message
            : errorThrown;
        error( err );
    }

})( jQuery );
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
        validateForm() && updateProfile( $(this) );
    });

    function updateProfile( $form ) {
        $sendButton.button( "option", "disabled", true );
        jsalon.ajaxSendForm( "/jsalon/rest/profile", "PUT", $form )
            .done(function () {
                showMessage( "Сохранение успешно выполнено", "done" );
                clearForm();
            })
            .fail(function ( jqXHR, textStatus, errorThrown ) {
                error( jsalon.getAjaxError( jqXHR, errorThrown ) );
            })
            .always(function () {
                $sendButton.button( "option", "disabled", false );
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

})( jQuery );
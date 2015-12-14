function pizarra() {
    if ($("#iframepizarra").is(":visible")) {
        $("#iframepizarra").fadeOut("slow");
        /*$("#ivrs-btnpizarra").css({
            "background-image": "url(assets/btn_pizarra.png)"
        });*/
    }

    if ($("#iframepizarra").is(":hidden")) {
        $("#iframepizarra").fadeIn("slow");
        /*$("#ivrs-btnpizarra").css({
            "background-image": "url(assets/close_pizarra.png)"
        });*/
    }
}
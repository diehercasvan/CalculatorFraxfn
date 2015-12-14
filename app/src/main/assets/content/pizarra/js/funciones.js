//Funcion  carga  de paleta de colores 
var contColor = 0;
var linerColor = "#000000";
e = "";
grosor = 10;

function verMenuColor() {
    if ($("#selecColor").is(':visible')) {
        $("#selecColor").fadeOut('slow', function() {
            destruirTablaColores();
        });
    }
    if ($("#selecColor").is(':hidden')) {
        crearTablaColores();
        $("#selecColor").fadeIn('slow');
    }
}

function destruirTablaColores() {
    var table = document.getElementById("table");
    var i = 0;
    contColor = 0;
    linerColor = "#000000";
    for (i = 0; i < 2; i++) {
        table.deleteRow(0);
    }
}

function crearTablaColores() {
    var table = document.getElementById("table");
    var row;
    var cell;
    var i = 0;
    var j = 0;

    for (i = 0; i < 2; i++) {
        row = table.insertRow(i);

        for (var j = 0; j < 4; j++) {
            cell = row.insertCell(j);
            cell.style.background = listadoColores(contColor);
        }
    }
    $("#table tr td").click(function() {

        linerColor = $(this).css("background-color");
        pizarra(linerColor);
        //alert(linerColor);
    });
}

function listadoColores(datoColor) {
    color = "";
    switch (datoColor) {
        case 0:
            color = "#FF0000";
            break;
        case 1:
            color = "#FF7F00";
            break;
        case 2:
            color = "#FFFF00";
            break;
        case 3:
            color = "#7FFF00";
            break;
        case 4:
            color = "#007FFF";
            break;
        case 5:
            color = "#0000FF";
            break;
        case 6:
            color = "#7A4DFF";
            break;
        case 7:
            color = "#000000";
            break;
        case 8:
            color = "#FF007F";
            break;
    }
    contColor++;
    return color
}

$(window).load(
    function() {
        setTimeout(
            function() {
                window.scrollTo(0, 0);
            },
            100
        );
    }
);

// When The DOM loads, initialize the scripts.
function pizarra(dato) {
    var restorePoints = [];
    var canvas = $("canvas");
    var a = false;
    var b, c = "";
    var d = document.getElementById("can");
    e = d.getContext("2d");
    e.strokeStyle = dato;
    e.lineWidth = grosor;
    e.lineCap = "round";
    $("#bsz").change(function(a) {
        grosor = this.value;
        e.lineWidth = grosor;
    });

    $(document).click(function() {
        a = false;
    });

    var isIPhone = (new RegExp("iPhone|iPod|iPad", "i")).test(navigator.userAgent);

    var getCanvasLocalCoordinates = function(pageX, pageY) {
        var position = canvas.offset();
        return ({
            x: (pageX - position.left),
            y: (pageY - position.top)
        });
    };

    var getTouchEvent = function(event) {
        return (isIPhone ? window.event.targetTouches[0] : event);
    };

    var onTouchStart = function(event) {
        //window.location.hash = '#';
        var touch = getTouchEvent(event);
        var localPosition = getCanvasLocalCoordinates(
            touch.pageX,
            touch.pageY
        );
        saveRestorePoint();
        a = true;
        e.save();
        b = localPosition.x;
        c = localPosition.y;
        if (a == true) {
            e.beginPath();
            e.moveTo(localPosition.x, localPosition.y);
            e.lineTo(localPosition.x + 1, localPosition.y + 1);
            e.stroke();
            e.closePath();
            b = localPosition.x;
            c = localPosition.y;
        }

        lastPenPoint = {
            x: localPosition.x,
            y: localPosition.y
        };

        canvas.bind((isIPhone ? "touchmove" : "mousemove"), onTouchMove);
        canvas.bind((isIPhone ? "touchend" : "mouseup"), onTouchEnd);
    };

    var OnClick = function(event) {
        var touch = getTouchEvent(event);
        var localPosition = getCanvasLocalCoordinates(
            touch.pageX,
            touch.pageY
        );

        lastPenPoint = {
            x: localPosition.x,
            y: localPosition.y
        };

        if (a == true) {
            e.beginPath();
            e.moveTo(localPosition.x, localPosition.y);
            e.lineTo(localPosition.x + 1, localPosition.y + 1);
            e.stroke();
            e.closePath();
            b = localPosition.x;
            c = localPosition.y;
        }
    };

    var onTouchMove = function(event) {
        var touch = getTouchEvent(event);
        var localPosition = getCanvasLocalCoordinates(
            touch.pageX,
            touch.pageY
        );

        lastPenPoint = {
            x: localPosition.x,
            y: localPosition.y
        };

        if (a == true) {
            e.beginPath();
            e.moveTo(localPosition.x, localPosition.y);
            e.lineTo(b, c);
            e.stroke();
            e.closePath();
            b = localPosition.x;
            c = localPosition.y;
        }
    };

    var onTouchEnd = function(event) {
        canvas.unbind(
            (isIPhone ? "touchmove" : "mousemove")
        );

        canvas.unbind(
            (isIPhone ? "touchend" : "mouseup")
        );
    };

    canvas.bind(
        (isIPhone ? "touchstart" : "mousedown"),
        function(event) {
            onTouchStart(event);
            return (false);
        }
    );

    $("#clear").click(function() {
        /*e.fillStyle="#fff";
        e.fillRect(0,0,d.width,d.height);
        e.strokeStyle="black";
        e.fillStyle="black";*/
        var canvas = document.getElementById("can");
        var ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);
    })
	
    $("#draft").click(function() {
        /*e.fillStyle="#fff";
        e.fillRect(0,0,d.width,d.height);
        e.strokeStyle="black";
        e.fillStyle="black";*/
        /*var canvas = document.getElementById("can");
        var ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);*/
        pizarra("rgba(255,255,255,0.1)");
    });
};

var restorePoints = [];

function saveRestorePoint() {
    var oCanvas = document.getElementById("can");
    var imgSrc = oCanvas.toDataURL("image/png");
    restorePoints.push(imgSrc);
}

$(document).ready(function(e) {
    pizarra("#000000");
});
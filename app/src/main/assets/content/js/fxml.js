function trim(myString) {
    return myString.replace(/^\s+/g, '').replace(/\s+$/g, '')
}

function leerXML(fichXML) {
    var xmlDoc = undefined;
    try {
        if (document.all) {
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        } else {
            xmlDoc = document.implementation.createDocument("", "", null);
        }
        xmlDoc.async = false;
        xmlDoc.load(fichXML);
    } catch (e) {
        try {
            var xmlhttp = new window.XMLHttpRequest();
            xmlhttp.open("GET", fichXML, false);
            xmlhttp.send(null);
            xmlDoc = xmlhttp.responseXML.documentElement;
            return xmlDoc;
        } catch (e) {
            return undefined;
        }
    }
    return xmlDoc;
}

function leerResultado(xmlDoc,nodo,subnodo,crf){
	var itemsXML = xmlDoc.getElementsByTagName(nodo);
	for (var k = 0; k < itemsXML.length; k++) {
		xmlFItem = itemsXML[k];
		if(xmlFItem.getAttribute('id')==crf){
			resultado = trim(xmlFItem.getElementsByTagName(subnodo)[0].firstChild.nodeValue);
			break;
		}
	}
	return resultado;
}
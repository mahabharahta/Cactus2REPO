/*!
* DevExtreme (dx.exporter.js)
* Version: 16.2.6
* Build date: Tue Mar 28 2017
*
* Copyright (c) 2012 - 2017 Developer Express Inc. ALL RIGHTS RESERVED
* EULA: https://www.devexpress.com/Support/EULAs/DevExtreme.xml
*/
"use strict";function normalizeHtml(e){var t=/xmlns="[\s\S]*?"/gi,n=!0;return e=e.replace(t,function(e){return n?(n=!1,e):""}),e.replace(/xmlns:NS1="[\s\S]*?"/gi,"").replace(/NS1:xmlns:xlink="([\s\S]*?)"/gi,'xmlns:xlink="$1"')}var $=jQuery,BaseWidget=DevExpress.viz.BaseWidget,registerComponent=DevExpress.registerComponent,DOMComponent=DevExpress.DOMComponent,FILE="file",ICON_TO="exportTo",ICON_PRINT="print",FORMATS_EXPORT=["PDF","PNG","SVG"],FORMATS_SUPPORTS=["JPEG","GIF"].concat(FORMATS_EXPORT),Exporter=DOMComponent.inherit({_killTracker:BaseWidget.prototype._killTracker,_getSvgElements:function(){var e=this,t=[];return $(e.getSourceContainer()).find("svg").each(function(e){t[e]=normalizeHtml($(this).clone().wrap("<div></div>").parent().html())}),JSON.stringify(t)},_appendTextArea:function(e,t,n){$("<textarea/>",{id:e,name:e,val:t}).appendTo(n)},_formSubmit:function(e){e.submit(),e.remove()},_getDefaultOptions:function(){return $.extend(this.callBase(),{redrawOnResize:!1,menuAlign:"right",exportFormat:FORMATS_EXPORT,printingEnabled:!0,fileName:FILE,showMenu:!0})},_createWindow:function(){return window.open("","printDiv","")},_createExportItems:function(e){var t=this;return $.map(e,function(e){return e=e.toUpperCase(),t.getSourceContainer().find("svg").length>1&&"SVG"===e?null:$.inArray(e.toUpperCase(),FORMATS_SUPPORTS)===-1?null:{name:e,text:e+" "+FILE}})},_render:function(){var e=this,t=e.option("fileName"),n=e._createExportItems(e.option("exportFormat")),r=$("<div />"),i=[{name:"export",icon:ICON_TO,items:n}],o={items:i,onItemClick:function(n){switch(n.itemData.name){case"print":e.print();break;case"export":break;default:e.exportTo(t,n.itemData.name)}}};e.option("showMenu")&&(e.option("printingEnabled")&&i.push({icon:ICON_PRINT,name:"print",click:function(){e.print()}}),r.dxMenu(o),e._$element.empty(),e._$element.append(r))},_exportSVG:function(e,t,n){var r=$("<form/>",{method:"POST",action:this.option("serverUrl"),enctype:"application/x-www-form-urlencoded",target:"_self",css:{display:"none",visibility:"hidden"}}),i=this._getSvgElements();this._appendTextArea("exportContent",n.clone().wrap("<div></div>").parent().html(),r),this._appendTextArea("svgElements",i,r),this._appendTextArea("fileName",e,r),this._appendTextArea("format",t.toLowerCase(),r),this._appendTextArea("width",n.width(),r),this._appendTextArea("height",n.height(),r),this._appendTextArea("url",window.location.host,r),$(document.body).append(r),this._formSubmit(r)},getSourceContainer:function(){return $(this.option("sourceContainer"))},print:function(){var e=this.getSourceContainer().html(),t=this._createWindow();t&&($(t.document.body).html(e),t.document.close(),t.focus(),t.print(),t.close())},exportTo:function(e,t){var n=this,r=n.getSourceContainer();r.find("svg").length&&n._exportSVG(e,t,r)}});registerComponent("dxExporter",Exporter),DevExpress.exporter={dxExporter:Exporter};

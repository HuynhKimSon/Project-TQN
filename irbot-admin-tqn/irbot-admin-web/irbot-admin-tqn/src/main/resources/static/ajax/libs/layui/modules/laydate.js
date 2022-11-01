/*! layDate v5.3.0 | 日期与时间组件 | The MIT License */
; !function () { "use strict"; var e = "lay", t = window.document, n = function (e) { return new a(e) }, a = function (e) { for (var n = 0, a = "object" == typeof e ? [e] : (this.selector = e, t.querySelectorAll(e || null)); n < a.length; n++)this.push(a[n]) }; a.prototype = [], a.prototype.constructor = a, n.extend = function () { var e = 1, t = arguments, n = function (e, t) { e = e || (t.constructor === Array ? [] : {}); for (var a in t) e[a] = t[a] && t[a].constructor === Object ? n(e[a], t[a]) : t[a]; return e }; for (t[0] = "object" == typeof t[0] ? t[0] : {}; e < t.length; e++)"object" == typeof t[e] && n(t[0], t[e]); return t[0] }, n.v = "1.0.6", n.ie = function () { var e = navigator.userAgent.toLowerCase(); return !!(window.ActiveXObject || "ActiveXObject" in window) && ((e.match(/msie\s(\d+)/) || [])[1] || "11") }(), n.getPath = function () { var e = t.currentScript ? t.currentScript.src : function () { for (var e, n = t.scripts, a = n.length - 1, i = a; i > 0; i--)if ("interactive" === n[i].readyState) { e = n[i].src; break } return e || n[a].src }(); return e.substring(0, e.lastIndexOf("/") + 1) }, n.stope = function (e) { e = e || window.event, e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0 }, n.each = function (e, t) { var n, a = this; if ("function" != typeof t) return a; if (e = e || [], e.constructor === Object) { for (n in e) if (t.call(e[n], n, e[n])) break } else for (n = 0; n < e.length && !t.call(e[n], n, e[n]); n++); return a }, n.digit = function (e, t, n) { var a = ""; e = String(e), t = t || 2; for (var i = e.length; i < t; i++)a += "0"; return e < Math.pow(10, t) ? a + (0 | e) : e }, n.elem = function (e, a) { var i = t.createElement(e); return n.each(a || {}, function (e, t) { i.setAttribute(e, t) }), i }, n.getStyle = function (e, t) { var n = e.currentStyle ? e.currentStyle : window.getComputedStyle(e, null); return n[n.getPropertyValue ? "getPropertyValue" : "getAttribute"](t) }, n.link = function (e, a, i) { var r = t.getElementsByTagName("head")[0], l = t.createElement("link"); "string" == typeof a && (i = a); var o = (i || e).replace(/\.|\//g, ""), s = "layuicss-" + o, c = "creating", y = 0; l.rel = "stylesheet", l.href = e, l.id = s, t.getElementById(s) || r.appendChild(l), "function" == typeof a && !function d(e) { var i = 100, r = t.getElementById(s); return ++y > 1e4 / i ? window.console && console.error(o + ".css: Invalid") : void (1989 === parseInt(n.getStyle(r, "width")) ? (e === c && r.removeAttribute("lay-status"), r.getAttribute("lay-status") === c ? setTimeout(d, i) : a()) : (r.setAttribute("lay-status", c), setTimeout(function () { d(c) }, i))) }() }, n.hasScrollbar = function () { return t.body.scrollHeight > (window.innerHeight || t.documentElement.clientHeight) }, n.position = function (e, a, i) { if (a) { i = i || {}, e !== t && e !== n("body")[0] || (i.clickType = "right"); var r = "right" === i.clickType ? function () { var e = i.e || window.event || {}; return { left: e.clientX, top: e.clientY, right: e.clientX, bottom: e.clientY } }() : e.getBoundingClientRect(), l = a.offsetWidth, o = a.offsetHeight, s = function (e) { return e = e ? "scrollLeft" : "scrollTop", t.body[e] | t.documentElement[e] }, c = function (e) { return t.documentElement[e ? "clientWidth" : "clientHeight"] }, y = 5, d = r.left, u = r.bottom; d + l + y > c("width") && (d = c("width") - l - y), u + o + y > c() && (r.top > o + y ? u = r.top - o - 2 * y : "right" === i.clickType && (u = c() - o - 2 * y, u < 0 && (u = 0))); var m = i.position; if (m && (a.style.position = m), a.style.left = d + ("fixed" === m ? 0 : s(1)) + "px", a.style.top = u + ("fixed" === m ? 0 : s()) + "px", !n.hasScrollbar()) { var h = a.getBoundingClientRect(); !i.SYSTEM_RELOAD && h.bottom + y > c() && (i.SYSTEM_RELOAD = !0, setTimeout(function () { n.position(e, a, i) }, 50)) } } }, n.options = function (e, t) { var a = n(e), i = t || "lay-options"; try { return new Function("return " + (a.attr(i) || "{}"))() } catch (r) { return hint.error("parseerror\uff1a" + r, "error"), {} } }, n.isTopElem = function (e) { var a = [t, n("body")[0]], i = !1; return n.each(a, function (t, n) { if (n === e) return i = !0 }), i }, a.addStr = function (e, t) { return e = e.replace(/\s+/, " "), t = t.replace(/\s+/, " ").split(" "), n.each(t, function (t, n) { new RegExp("\\b" + n + "\\b").test(e) || (e = e + " " + n) }), e.replace(/^\s|\s$/, "") }, a.removeStr = function (e, t) { return e = e.replace(/\s+/, " "), t = t.replace(/\s+/, " ").split(" "), n.each(t, function (t, n) { var a = new RegExp("\\b" + n + "\\b"); a.test(e) && (e = e.replace(a, "")) }), e.replace(/\s+/, " ").replace(/^\s|\s$/, "") }, a.prototype.find = function (e) { var t = this, a = 0, i = [], r = "object" == typeof e; return this.each(function (n, l) { for (var o = r ? l.contains(e) : l.querySelectorAll(e || null); a < o.length; a++)i.push(o[a]); t.shift() }), r || (t.selector = (t.selector ? t.selector + " " : "") + e), n.each(i, function (e, n) { t.push(n) }), t }, a.prototype.each = function (e) { return n.each.call(this, this, e) }, a.prototype.addClass = function (e, t) { return this.each(function (n, i) { i.className = a[t ? "removeStr" : "addStr"](i.className, e) }) }, a.prototype.removeClass = function (e) { return this.addClass(e, !0) }, a.prototype.hasClass = function (e) { var t = !1; return this.each(function (n, a) { new RegExp("\\b" + e + "\\b").test(a.className) && (t = !0) }), t }, a.prototype.css = function (e, t) { var a = this, i = function (e) { return isNaN(e) ? e : e + "px" }; return "string" == typeof e && void 0 === t ? function () { if (a.length > 0) return a[0].style[e] }() : a.each(function (a, r) { "object" == typeof e ? n.each(e, function (e, t) { r.style[e] = i(t) }) : r.style[e] = i(t) }) }, a.prototype.width = function (e) { var t = this; return void 0 === e ? function () { if (t.length > 0) return t[0].offsetWidth }() : t.each(function (n, a) { t.css("width", e) }) }, a.prototype.height = function (e) { var t = this; return void 0 === e ? function () { if (t.length > 0) return t[0].offsetHeight }() : t.each(function (n, a) { t.css("height", e) }) }, a.prototype.attr = function (e, t) { var n = this; return void 0 === t ? function () { if (n.length > 0) return n[0].getAttribute(e) }() : n.each(function (n, a) { a.setAttribute(e, t) }) }, a.prototype.removeAttr = function (e) { return this.each(function (t, n) { n.removeAttribute(e) }) }, a.prototype.html = function (e) { var t = this; return void 0 === e ? function () { if (t.length > 0) return t[0].innerHTML }() : this.each(function (t, n) { n.innerHTML = e }) }, a.prototype.val = function (e) { var t = this; return void 0 === e ? function () { if (t.length > 0) return t[0].value }() : this.each(function (t, n) { n.value = e }) }, a.prototype.append = function (e) { return this.each(function (t, n) { "object" == typeof e ? n.appendChild(e) : n.innerHTML = n.innerHTML + e }) }, a.prototype.remove = function (e) { return this.each(function (t, n) { e ? n.removeChild(e) : n.parentNode.removeChild(n) }) }, a.prototype.on = function (e, t) { return this.each(function (n, a) { a.attachEvent ? a.attachEvent("on" + e, function (e) { e.target = e.srcElement, t.call(a, e) }) : a.addEventListener(e, t, !1) }) }, a.prototype.off = function (e, t) { return this.each(function (n, a) { a.detachEvent ? a.detachEvent("on" + e, t) : a.removeEventListener(e, t, !1) }) }, window.lay = n, window.layui && layui.define && layui.define(function (t) { t(e, n) }) }(), !function (e) { "use strict"; var t = e.layui && layui.define, n = { getPath: e.lay && lay.getPath ? lay.getPath() : "", link: function (t, n, a) { i.path && e.lay && lay.link && lay.link(i.path + t, n, a) } }, a = e.LAYUI_GLOBAL || {}, i = { v: "5.3.0", config: {}, index: e.laydate && e.laydate.v ? 1e5 : 0, path: a.laydate_dir || n.getPath, set: function (e) { var t = this; return t.config = lay.extend({}, t.config, e), t }, ready: function (e) { var a = "laydate", r = "", l = (t ? "modules/laydate/" : "theme/") + "default/laydate.css?v=" + i.v + r; return t ? layui.addcss(l, e, a) : n.link(l, e, a), this } }, r = function () { var e = this, t = e.config, n = t.id; return r.that[n] = e, { hint: function (t) { e.hint.call(e, t) }, config: e.config } }, l = "laydate", o = ".layui-laydate", s = "layui-this", c = "laydate-disabled", y = [100, 2e5], d = "layui-laydate-static", u = "layui-laydate-list", m = "layui-laydate-hint", h = "layui-laydate-footer", f = ".laydate-btns-confirm", p = "laydate-time-text", g = "laydate-btns-time", v = "layui-laydate-preview", T = function (e) { var t = this; t.index = ++i.index, t.config = lay.extend({}, t.config, i.config, e), e = t.config, e.id = "id" in e ? e.id : t.index, i.ready(function () { t.init() }) }, D = "yyyy|y|MM|M|dd|d|HH|H|mm|m|ss|s"; r.formatArr = function (e) { return (e || "").match(new RegExp(D + "|.", "g")) || [] }, T.isLeapYear = function (e) { return e % 4 === 0 && e % 100 !== 0 || e % 400 === 0 }, T.prototype.config = { type: "date", range: !1, format: "yyyy-MM-dd", value: null, isInitValue: !0, min: "1900-1-1", max: "2099-12-31", trigger: "click", show: !1, showBottom: !0, isPreview: !0, btns: ["clear", "now", "confirm"], lang: "vi", theme: "default", position: null, calendar: !1, mark: {}, zIndex: null, done: null, change: null }, T.prototype.lang = function () { var e = this, t = e.config, n = { cn: { weeks: ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"], time: ["\u65f6", "\u5206", "\u79d2"], timeTips: "\u9009\u62e9\u65f6\u95f4", startTime: "\u5f00\u59cb\u65f6\u95f4", endTime: "\u7ed3\u675f\u65f6\u95f4", dateTips: "\u8fd4\u56de\u65e5\u671f", month: ["\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d", "\u4e03", "\u516b", "\u4e5d", "\u5341", "\u5341\u4e00", "\u5341\u4e8c"], tools: { confirm: "\u786e\u5b9a", clear: "\u6e05\u7a7a", now: "\u73b0\u5728" }, timeout: "\u7ed3\u675f\u65f6\u95f4\u4e0d\u80fd\u65e9\u4e8e\u5f00\u59cb\u65f6\u95f4<br>\u8bf7\u91cd\u65b0\u9009\u62e9", invalidDate: "\u4e0d\u5728\u6709\u6548\u65e5\u671f\u6216\u65f6\u95f4\u8303\u56f4\u5185", formatError: ["\u65e5\u671f\u683c\u5f0f\u4e0d\u5408\u6cd5<br>\u5fc5\u987b\u9075\u5faa\u4e0b\u8ff0\u683c\u5f0f\uff1a<br>", "<br>\u5df2\u4e3a\u4f60\u91cd\u7f6e"], preview: "\u5f53\u524d\u9009\u4e2d\u7684\u7ed3\u679c" }, en: { weeks: ["Su1", "Mo", "Tu", "We", "Th", "Fr", "Sa"], time: ["Hours", "Minutes", "Seconds"], timeTips: "Select Time", startTime: "Start Time", endTime: "End Time", dateTips: "Select Date", month: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"], tools: { confirm: "Confirm", clear: "Clear", now: "Now" }, timeout: "End time cannot be less than start Time<br>Please re-select", invalidDate: "Invalid date", formatError: ["The date format error<br>Must be followed\uff1a<br>", "<br>It has been reset"], preview: "The selected result" }, vi: { weeks: ["CN", "T2", "T3", "T4", "T5", "T6", "T7"], time: ["Giờ", "Phút", "Giây"], timeTips: "Chọn thời gian", startTime: "Thời gian bắt đầu", endTime: "Thời gian kết thúc", dateTips: "Chọn ngày", month: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"], tools: { confirm: "Xác nhận", clear: "Xóa", now: "Hiện tại" }, timeout: "Thời gian kết thúc không được nhỏ hơn thời gian bắt đầu<br>Vui lòng chọn lại", invalidDate: "Invalid date", formatError: ["The date format error<br>Must be followed\uff1a<br>", "<br>It has been reset"], preview: "Kết quả chọn" } }; return n[t.lang] || n.cn }, T.prototype.init = function () { var t = this, n = t.config, a = "static" === n.position, i = { year: "yyyy", month: "yyyy-MM", date: "yyyy-MM-dd", time: "HH:mm:ss", datetime: "yyyy-MM-dd HH:mm:ss" }; n.elem = lay(n.elem), n.eventElem = lay(n.eventElem), n.elem[0] && (t.rangeStr = n.range ? "string" == typeof n.range ? n.range : "-" : "", n.range && n.range.constructor === Array && (t.rangeElem = [lay(n.range[0]), lay(n.range[1])]), i[n.type] || (e.console && console.error && console.error("laydate type error:'" + n.type + "' is not supported"), n.type = "date"), n.format === i.date && (n.format = i[n.type] || i.date), t.format = r.formatArr(n.format), t.EXP_IF = "", t.EXP_SPLIT = "", lay.each(t.format, function (e, n) { var a = new RegExp(D).test(n) ? "\\d{" + function () { return new RegExp(D).test(t.format[0 === e ? e + 1 : e - 1] || "") ? /^yyyy|y$/.test(n) ? 4 : n.length : /^yyyy$/.test(n) ? "1,4" : /^y$/.test(n) ? "1,308" : "1,2" }() + "}" : "\\" + n; t.EXP_IF = t.EXP_IF + a, t.EXP_SPLIT = t.EXP_SPLIT + "(" + a + ")" }), t.EXP_IF_ONE = new RegExp("^" + t.EXP_IF + "$"), t.EXP_IF = new RegExp("^" + (n.range ? t.EXP_IF + "\\s\\" + t.rangeStr + "\\s" + t.EXP_IF : t.EXP_IF) + "$"), t.EXP_SPLIT = new RegExp("^" + t.EXP_SPLIT + "$", ""), t.isInput(n.elem[0]) || "focus" === n.trigger && (n.trigger = "click"), n.elem.attr("lay-key") || (n.elem.attr("lay-key", t.index), n.eventElem.attr("lay-key", t.index)), n.mark = lay.extend({}, n.calendar && "cn" === n.lang ? { "0-1-1": "\u5143\u65e6", "0-2-14": "\u60c5\u4eba", "0-3-8": "\u5987\u5973", "0-3-12": "\u690d\u6811", "0-4-1": "\u611a\u4eba", "0-5-1": "\u52b3\u52a8", "0-5-4": "\u9752\u5e74", "0-6-1": "\u513f\u7ae5", "0-9-10": "\u6559\u5e08", "0-9-18": "\u56fd\u803b", "0-10-1": "\u56fd\u5e86", "0-12-25": "\u5723\u8bde" } : {}, n.mark), lay.each(["min", "max"], function (e, t) { var a = [], i = []; if ("number" == typeof n[t]) { var r = n[t], l = (new Date).getTime(), o = 864e5, s = new Date(r ? r < o ? l + r * o : r : l); a = [s.getFullYear(), s.getMonth() + 1, s.getDate()], r < o || (i = [s.getHours(), s.getMinutes(), s.getSeconds()]) } else a = (n[t].match(/\d+-\d+-\d+/) || [""])[0].split("-"), i = (n[t].match(/\d+:\d+:\d+/) || [""])[0].split(":"); n[t] = { year: 0 | a[0] || (new Date).getFullYear(), month: a[1] ? (0 | a[1]) - 1 : (new Date).getMonth(), date: 0 | a[2] || (new Date).getDate(), hours: 0 | i[0], minutes: 0 | i[1], seconds: 0 | i[2] } }), t.elemID = "layui-laydate" + n.elem.attr("lay-key"), (n.show || a) && t.render(), a || t.events(), n.value && n.isInitValue && (n.value.constructor === Date ? t.setValue(t.parse(0, t.systemDate(n.value))) : t.setValue(n.value))) }, T.prototype.render = function () { var e = this, t = e.config, n = e.lang(), a = "static" === t.position, r = e.elem = lay.elem("div", { id: e.elemID, "class": ["layui-laydate", t.range ? " layui-laydate-range" : "", a ? " " + d : "", t.theme && "default" !== t.theme && !/^#/.test(t.theme) ? " laydate-theme-" + t.theme : ""].join("") }), l = e.elemMain = [], o = e.elemHeader = [], s = e.elemCont = [], c = e.table = [], y = e.footer = lay.elem("div", { "class": h }); if (t.zIndex && (r.style.zIndex = t.zIndex), lay.each(new Array(2), function (e) { if (!t.range && e > 0) return !0; var a = lay.elem("div", { "class": "layui-laydate-header" }), i = [function () { var e = lay.elem("i", { "class": "layui-icon laydate-icon laydate-prev-y" }); return e.innerHTML = "&#xe65a;", e }(), function () { var e = lay.elem("i", { "class": "layui-icon laydate-icon laydate-prev-m" }); return e.innerHTML = "&#xe603;", e }(), function () { var e = lay.elem("div", { "class": "laydate-set-ym" }), t = lay.elem("span"), n = lay.elem("span"); return e.appendChild(t), e.appendChild(n), e }(), function () { var e = lay.elem("i", { "class": "layui-icon laydate-icon laydate-next-m" }); return e.innerHTML = "&#xe602;", e }(), function () { var e = lay.elem("i", { "class": "layui-icon laydate-icon laydate-next-y" }); return e.innerHTML = "&#xe65b;", e }()], r = lay.elem("div", { "class": "layui-laydate-content" }), y = lay.elem("table"), d = lay.elem("thead"), u = lay.elem("tr"); lay.each(i, function (e, t) { a.appendChild(t) }), d.appendChild(u), lay.each(new Array(6), function (e) { var t = y.insertRow(0); lay.each(new Array(7), function (a) { if (0 === e) { var i = lay.elem("th"); i.innerHTML = n.weeks[a], u.appendChild(i) } t.insertCell(a) }) }), y.insertBefore(d, y.children[0]), r.appendChild(y), l[e] = lay.elem("div", { "class": "layui-laydate-main laydate-main-list-" + e }), l[e].appendChild(a), l[e].appendChild(r), o.push(i), s.push(r), c.push(y) }), lay(y).html(function () { var e = [], i = []; return "datetime" === t.type && e.push('<span lay-type="datetime" class="' + g + '">' + n.timeTips + "</span>"), (t.range || "datetime" !== t.type) && e.push('<span class="' + v + '" title="' + n.preview + '"></span>'), lay.each(t.btns, function (e, r) { var l = n.tools[r] || "btn"; t.range && "now" === r || (a && "clear" === r && (l = "cn" === t.lang ? "\u91cd\u7f6e" : "Reset"), i.push('<span lay-type="' + r + '" class="laydate-btns-' + r + '">' + l + "</span>")) }), e.push('<div class="laydate-footer-btns">' + i.join("") + "</div>"), e.join("") }()), lay.each(l, function (e, t) { r.appendChild(t) }), t.showBottom && r.appendChild(y), /^#/.test(t.theme)) { var u = lay.elem("style"), m = ["#{{id}} .layui-laydate-header{background-color:{{theme}};}", "#{{id}} .layui-this{background-color:{{theme}} !important;}"].join("").replace(/{{id}}/g, e.elemID).replace(/{{theme}}/g, t.theme); "styleSheet" in u ? (u.setAttribute("type", "text/css"), u.styleSheet.cssText = m) : u.innerHTML = m, lay(r).addClass("laydate-theme-molv"), r.appendChild(u) } i.thisId = t.id, e.remove(T.thisElemDate), a ? t.elem.append(r) : (document.body.appendChild(r), e.position()), e.checkDate().calendar(null, 0, "init"), e.changeEvent(), T.thisElemDate = e.elemID, "function" == typeof t.ready && t.ready(lay.extend({}, t.dateTime, { month: t.dateTime.month + 1 })), e.preview() }, T.prototype.remove = function (e) { var t = this, n = (t.config, lay("#" + (e || t.elemID))); return n[0] ? (n.hasClass(d) || t.checkDate(function () { n.remove() }), t) : t }, T.prototype.position = function () { var e = this, t = e.config; return lay.position(e.bindElem || t.elem[0], e.elem, { position: t.position }), e }, T.prototype.hint = function (e) { var t = this, n = (t.config, lay.elem("div", { "class": m })); t.elem && (n.innerHTML = e || "", lay(t.elem).find("." + m).remove(), t.elem.appendChild(n), clearTimeout(t.hinTimer), t.hinTimer = setTimeout(function () { lay(t.elem).find("." + m).remove() }, 3e3)) }, T.prototype.getAsYM = function (e, t, n) { return n ? t-- : t++, t < 0 && (t = 11, e--), t > 11 && (t = 0, e++), [e, t] }, T.prototype.systemDate = function (e) { var t = e || new Date; return { year: t.getFullYear(), month: t.getMonth(), date: t.getDate(), hours: e ? e.getHours() : 0, minutes: e ? e.getMinutes() : 0, seconds: e ? e.getSeconds() : 0 } }, T.prototype.checkDate = function (e) { var t, n, a = this, r = (new Date, a.config), l = a.lang(), o = r.dateTime = r.dateTime || a.systemDate(), s = a.bindElem || r.elem[0], c = (a.isInput(s) ? "val" : "html", function () { if (a.rangeElem) { var e = [a.rangeElem[0].val(), a.rangeElem[1].val()]; if (e[0] && e[1]) return e.join(" " + a.rangeStr + " ") } return a.isInput(s) ? s.value : "static" === r.position ? "" : lay(s).attr("lay-date") }()), d = function (e) { e.year > y[1] && (e.year = y[1], n = !0), e.month > 11 && (e.month = 11, n = !0), e.hours > 23 && (e.hours = 0, n = !0), e.minutes > 59 && (e.minutes = 0, e.hours++, n = !0), e.seconds > 59 && (e.seconds = 0, e.minutes++, n = !0), t = i.getEndDate(e.month + 1, e.year), e.date > t && (e.date = t, n = !0) }, u = function (e, t, i) { var l = ["startTime", "endTime"]; t = (t.match(a.EXP_SPLIT) || []).slice(1), i = i || 0, r.range && (a[l[i]] = a[l[i]] || {}), lay.each(a.format, function (o, s) { var c = parseFloat(t[o]); t[o].length < s.length && (n = !0), /yyyy|y/.test(s) ? (c < y[0] && (c = y[0], n = !0), e.year = c) : /MM|M/.test(s) ? (c < 1 && (c = 1, n = !0), e.month = c - 1) : /dd|d/.test(s) ? (c < 1 && (c = 1, n = !0), e.date = c) : /HH|H/.test(s) ? (c < 1 && (c = 0, n = !0), e.hours = c, r.range && (a[l[i]].hours = c)) : /mm|m/.test(s) ? (c < 1 && (c = 0, n = !0), e.minutes = c, r.range && (a[l[i]].minutes = c)) : /ss|s/.test(s) && (c < 1 && (c = 0, n = !0), e.seconds = c, r.range && (a[l[i]].seconds = c)) }), d(e) }; if ("limit" === e) return d(o), a; c = c || r.value, "string" == typeof c && (c = c.replace(/\s+/g, " ").replace(/^\s|\s$/g, "")); var m = function () { r.range && (a.endDate = a.endDate || lay.extend({}, r.dateTime, function () { var e = {}, t = r.dateTime, n = a.getAsYM(t.year, t.month); return "year" === r.type ? e.year = t.year + 1 : "time" !== r.type && (e.year = n[0], e.month = n[1]), "datetime" !== r.type && "time" !== r.type || (e.hours = 23, e.minutes = e.seconds = 59), e }())) }; m(), "string" == typeof c && c ? a.EXP_IF.test(c) ? r.range ? (c = c.split(" " + a.rangeStr + " "), lay.each([r.dateTime, a.endDate], function (e, t) { u(t, c[e], e) })) : u(o, c) : (a.hint(l.formatError[0] + (r.range ? r.format + " " + a.rangeStr + " " + r.format : r.format) + l.formatError[1]), n = !0) : c && c.constructor === Date ? r.dateTime = a.systemDate(c) : (r.dateTime = a.systemDate(), delete a.startTime, delete a.endDate, m(), delete a.endTime), function () { if (a.rangeElem) { var e = [a.rangeElem[0].val(), a.rangeElem[1].val()], t = [r.dateTime, a.endDate]; lay.each(e, function (e, n) { a.EXP_IF_ONE.test(n) && u(t[e], n, e) }) } }(), d(o), r.range && d(a.endDate), n && c && a.setValue(r.range ? a.endDate ? a.parse() : "" : a.parse()); var h = function (e) { return a.newDate(e).getTime() }; return (h(o) > h(r.max) || h(o) < h(r.min)) && (o = r.dateTime = lay.extend({}, r.min)), r.range && (h(a.endDate) < h(r.min) || h(a.endDate) > h(r.max)) && (a.endDate = lay.extend({}, r.max)), e && e(), a }, T.prototype.mark = function (e, t) { var n, a = this, i = a.config; return lay.each(i.mark, function (e, a) { var i = e.split("-"); i[0] != t[0] && 0 != i[0] || i[1] != t[1] && 0 != i[1] || i[2] != t[2] || (n = a || t[2]) }), n && e.html('<span class="laydate-day-mark">' + n + "</span>"), a }, T.prototype.limit = function (e, t, n, a) { var i, r = this, l = r.config, o = {}, s = l[n > 41 ? "endDate" : "dateTime"], y = lay.extend({}, s, t || {}); return lay.each({ now: y, min: l.min, max: l.max }, function (e, t) { o[e] = r.newDate(lay.extend({ year: t.year, month: t.month, date: t.date }, function () { var e = {}; return lay.each(a, function (n, a) { e[a] = t[a] }), e }())).getTime() }), i = o.now < o.min || o.now > o.max, e && e[i ? "addClass" : "removeClass"](c), i }, T.prototype.thisDateTime = function (e) { var t = this, n = t.config; return e ? t.endDate : n.dateTime }, T.prototype.calendar = function (e, t, n) { var a, r, l, o = this, c = o.config, t = t ? 1 : 0, d = e || o.thisDateTime(t), u = new Date, m = o.lang(), h = "date" !== c.type && "datetime" !== c.type, p = lay(o.table[t]).find("td"), g = lay(o.elemHeader[t][2]).find("span"); return d.year < y[0] && (d.year = y[0], o.hint(m.invalidDate)), d.year > y[1] && (d.year = y[1], o.hint(m.invalidDate)), o.firstDate || (o.firstDate = lay.extend({}, d)), u.setFullYear(d.year, d.month, 1), a = u.getDay(), r = i.getEndDate(d.month || 12, d.year), l = i.getEndDate(d.month + 1, d.year), lay.each(p, function (e, t) { var n = [d.year, d.month], i = 0; t = lay(t), t.removeAttr("class"), e < a ? (i = r - a + e, t.addClass("laydate-day-prev"), n = o.getAsYM(d.year, d.month, "sub")) : e >= a && e < l + a ? (i = e - a, i + 1 === d.date && t.addClass(s)) : (i = e - l - a, t.addClass("laydate-day-next"), n = o.getAsYM(d.year, d.month)), n[1]++, n[2] = i + 1, t.attr("lay-ymd", n.join("-")).html(n[2]), o.mark(t, n).limit(t, { year: n[0], month: n[1] - 1, date: n[2] }, e) }), lay(g[0]).attr("lay-ym", d.year + "-" + (d.month + 1)), lay(g[1]).attr("lay-ym", d.year + "-" + (d.month + 1)), "cn" === c.lang ? (lay(g[0]).attr("lay-type", "year").html(d.year + " \u5e74"), lay(g[1]).attr("lay-type", "month").html(d.month + 1 + " \u6708")) : (lay(g[0]).attr("lay-type", "month").html(m.month[d.month]), lay(g[1]).attr("lay-type", "year").html(d.year)), h && (c.range ? e && (o.listYM = [[c.dateTime.year, c.dateTime.month + 1], [o.endDate.year, o.endDate.month + 1]], o.list(c.type, 0).list(c.type, 1), "time" === c.type ? o.setBtnStatus("\u65f6\u95f4", lay.extend({}, o.systemDate(), o.startTime), lay.extend({}, o.systemDate(), o.endTime)) : o.setBtnStatus(!0)) : (o.listYM = [[d.year, d.month + 1]], o.list(c.type, 0))), c.range && "init" === n && !e && o.calendar(o.endDate, 1), c.range || o.limit(lay(o.footer).find(f), null, 0, ["hours", "minutes", "seconds"]), o.setBtnStatus(), o }, T.prototype.list = function (e, t) { var n = this, a = n.config, i = a.dateTime, r = n.lang(), l = a.range && "date" !== a.type && "datetime" !== a.type, o = lay.elem("ul", { "class": u + " " + { year: "laydate-year-list", month: "laydate-month-list", time: "laydate-time-list" }[e] }), y = n.elemHeader[t], d = lay(y[2]).find("span"), m = n.elemCont[t || 0], h = lay(m).find("." + u)[0], v = "cn" === a.lang, T = v ? "\u5e74" : "", D = n.listYM[t] || {}, w = ["hours", "minutes", "seconds"], E = ["startTime", "endTime"][t]; if (D[0] < 1 && (D[0] = 1), "year" === e) { var x, b = x = D[0] - 7; b < 1 && (b = x = 1), lay.each(new Array(15), function (e) { var i = lay.elem("li", { "lay-ym": x }), r = { year: x }; x == D[0] && lay(i).addClass(s), i.innerHTML = x + T, o.appendChild(i), x < n.firstDate.year ? (r.month = a.min.month, r.date = a.min.date) : x >= n.firstDate.year && (r.month = a.max.month, r.date = a.max.date), n.limit(lay(i), r, t), x++ }), lay(d[v ? 0 : 1]).attr("lay-ym", x - 8 + "-" + D[1]).html(b + T + " - " + (x - 1 + T)) } else if ("month" === e) lay.each(new Array(12), function (e) { var i = lay.elem("li", { "lay-ym": e }), l = { year: D[0], month: e }; e + 1 == D[1] && lay(i).addClass(s), i.innerHTML = r.month[e] + (v ? "\u6708" : ""), o.appendChild(i), D[0] < n.firstDate.year ? l.date = a.min.date : D[0] >= n.firstDate.year && (l.date = a.max.date), n.limit(lay(i), l, t) }), lay(d[v ? 0 : 1]).attr("lay-ym", D[0] + "-" + D[1]).html(D[0] + T); else if ("time" === e) { var M = function () { lay(o).find("ol").each(function (e, a) { lay(a).find("li").each(function (a, i) { n.limit(lay(i), [{ hours: a }, { hours: n[E].hours, minutes: a }, { hours: n[E].hours, minutes: n[E].minutes, seconds: a }][e], t, [["hours"], ["hours", "minutes"], ["hours", "minutes", "seconds"]][e]) }) }), a.range || n.limit(lay(n.footer).find(f), n[E], 0, ["hours", "minutes", "seconds"]) }; a.range ? n[E] || (n[E] = "startTime" === E ? i : n.endDate) : n[E] = i, lay.each([24, 60, 60], function (e, t) { var a = lay.elem("li"), i = ["<p>" + r.time[e] + "</p><ol>"]; lay.each(new Array(t), function (t) { i.push("<li" + (n[E][w[e]] === t ? ' class="' + s + '"' : "") + ">" + lay.digit(t, 2) + "</li>") }), a.innerHTML = i.join("") + "</ol>", o.appendChild(a) }), M() } if (h && m.removeChild(h), m.appendChild(o), "year" === e || "month" === e) lay(n.elemMain[t]).addClass("laydate-ym-show"), lay(o).find("li").on("click", function () { var r = 0 | lay(this).attr("lay-ym"); if (!lay(this).hasClass(c)) { 0 === t ? (i[e] = r, n.limit(lay(n.footer).find(f), null, 0)) : n.endDate[e] = r; var y = "year" === a.type || "month" === a.type; y ? (lay(o).find("." + s).removeClass(s), lay(this).addClass(s), "month" === a.type && "year" === e && (n.listYM[t][0] = r, l && (t ? i.year = r : n.endDate.year = r), n.list("month", t))) : (n.checkDate("limit").calendar(null, t), n.closeList()), n.setBtnStatus(), a.range || ("month" === a.type && "month" === e || "year" === a.type && "year" === e) && n.setValue(n.parse()).remove().done(), n.done(null, "change"), lay(n.footer).find("." + g).removeClass(c) } }); else { var C = lay.elem("span", { "class": p }), S = function () { lay(o).find("ol").each(function (e) { var t = this, a = lay(t).find("li"); t.scrollTop = 30 * (n[E][w[e]] - 2), t.scrollTop <= 0 && a.each(function (e, n) { if (!lay(this).hasClass(c)) return t.scrollTop = 30 * (e - 2), !0 }) }) }, I = lay(y[2]).find("." + p); S(), C.innerHTML = a.range ? [r.startTime, r.endTime][t] : r.timeTips, lay(n.elemMain[t]).addClass("laydate-time-show"), I[0] && I.remove(), y[2].appendChild(C), lay(o).find("ol").each(function (e) { var t = this; lay(t).find("li").on("click", function () { var r = 0 | this.innerHTML; lay(this).hasClass(c) || (a.range ? n[E][w[e]] = r : i[w[e]] = r, lay(t).find("." + s).removeClass(s), lay(this).addClass(s), M(), S(), (n.endDate || "time" === a.type) && n.done(null, "change"), n.setBtnStatus()) }) }) } return n }, T.prototype.listYM = [], T.prototype.closeList = function () { var e = this; e.config; lay.each(e.elemCont, function (t, n) { lay(this).find("." + u).remove(), lay(e.elemMain[t]).removeClass("laydate-ym-show laydate-time-show") }), lay(e.elem).find("." + p).remove() }, T.prototype.setBtnStatus = function (e, t, n) { var a, i = this, r = i.config, l = i.lang(), o = lay(i.footer).find(f); r.range && "time" !== r.type && (t = t || r.dateTime, n = n || i.endDate, a = i.newDate(t).getTime() > i.newDate(n).getTime(), i.limit(null, t) || i.limit(null, n) ? o.addClass(c) : o[a ? "addClass" : "removeClass"](c), e && a && i.hint("string" == typeof e ? l.timeout.replace(/\u65e5\u671f/g, e) : l.timeout)) }, T.prototype.parse = function (e, t) { var n = this, a = n.config, r = t || ("end" == e ? lay.extend({}, n.endDate, n.endTime) : a.range ? lay.extend({}, a.dateTime, n.startTime) : a.dateTime), l = i.parse(r, n.format, 1); return a.range && void 0 === e ? l + " " + n.rangeStr + " " + n.parse("end") : l }, T.prototype.newDate = function (e) { return e = e || {}, new Date(e.year || 1, e.month || 0, e.date || 1, e.hours || 0, e.minutes || 0, e.seconds || 0) }, T.prototype.setValue = function (e) { var t = this, n = t.config, a = t.bindElem || n.elem[0]; return "static" === n.position ? t : (e = e || "", t.isInput(a) ? lay(a).val(e) : t.rangeElem ? (t.rangeElem[0].val(e ? t.parse("start") : ""), t.rangeElem[1].val(e ? t.parse("end") : "")) : (0 === lay(a).find("*").length && lay(a).html(e), lay(a).attr("lay-date", e)), t) }, T.prototype.preview = function () { var e = this, t = e.config; if (t.isPreview) { var n = lay(e.elem).find("." + v), a = t.range ? e.endDate ? e.parse() : "" : e.parse(); n.html(a).css({ color: "#5FB878", "font-size": "14px;" }), setTimeout(function () { n.css({ color: "#666", "font-size": "12px;" }) }, 300) } }, T.prototype.done = function (e, t) { var n = this, a = n.config, i = lay.extend({}, lay.extend(a.dateTime, n.startTime)), r = lay.extend({}, lay.extend(n.endDate, n.endTime)); return lay.each([i, r], function (e, t) { "month" in t && lay.extend(t, { month: t.month + 1 }) }), n.preview(), e = e || [n.parse(), i, r], "function" == typeof a[t || "done"] && a[t || "done"].apply(a, e), n }, T.prototype.choose = function (e, t) { var n = this, a = n.config, i = n.thisDateTime(t), r = (lay(n.elem).find("td"), e.attr("lay-ymd").split("-")); r = { year: 0 | r[0], month: (0 | r[1]) - 1, date: 0 | r[2] }, e.hasClass(c) || (lay.extend(i, r), a.range ? (lay.each(["startTime", "endTime"], function (e, t) { n[t] = n[t] || { hours: 0, minutes: 0, seconds: 0 } }), n.calendar(null, t).done(null, "change")) : "static" === a.position ? n.calendar().done().done(null, "change") : "date" === a.type ? n.setValue(n.parse()).remove().done() : "datetime" === a.type && n.calendar().done(null, "change")) }, T.prototype.tool = function (e, t) { var n = this, a = n.config, i = n.lang(), r = a.dateTime, l = "static" === a.position, o = { datetime: function () { lay(e).hasClass(c) || (n.list("time", 0), a.range && n.list("time", 1), lay(e).attr("lay-type", "date").html(n.lang().dateTips)) }, date: function () { n.closeList(), lay(e).attr("lay-type", "datetime").html(n.lang().timeTips) }, clear: function () { l && (lay.extend(r, n.firstDate), n.calendar()), a.range && (delete a.dateTime, delete n.endDate, delete n.startTime, delete n.endTime), n.setValue("").remove(), n.done(["", {}, {}]) }, now: function () { var e = new Date; lay.extend(r, n.systemDate(), { hours: e.getHours(), minutes: e.getMinutes(), seconds: e.getSeconds() }), n.setValue(n.parse()).remove(), l && n.calendar(), n.done() }, confirm: function () { if (a.range) { if (lay(e).hasClass(c)) return n.hint("time" === a.type ? i.timeout.replace(/\u65e5\u671f/g, "\u65f6\u95f4") : i.timeout) } else if (lay(e).hasClass(c)) return n.hint(i.invalidDate); n.done(), n.setValue(n.parse()).remove() } }; o[t] && o[t]() }, T.prototype.change = function (e) { var t = this, n = t.config, a = t.thisDateTime(e), i = n.range && ("year" === n.type || "month" === n.type), r = t.elemCont[e || 0], l = t.listYM[e], o = function (o) { var s = lay(r).find(".laydate-year-list")[0], c = lay(r).find(".laydate-month-list")[0]; return s && (l[0] = o ? l[0] - 15 : l[0] + 15, t.list("year", e)), c && (o ? l[0]-- : l[0]++, t.list("month", e)), (s || c) && (lay.extend(a, { year: l[0] }), i && (a.year = l[0]), n.range || t.done(null, "change"), n.range || t.limit(lay(t.footer).find(f), { year: l[0] })), t.setBtnStatus(), s || c }; return { prevYear: function () { o("sub") || (a.year--, t.checkDate("limit").calendar(null, e), t.done(null, "change")) }, prevMonth: function () { var n = t.getAsYM(a.year, a.month, "sub"); lay.extend(a, { year: n[0], month: n[1] }), t.checkDate("limit").calendar(null, e), t.done(null, "change") }, nextMonth: function () { var n = t.getAsYM(a.year, a.month); lay.extend(a, { year: n[0], month: n[1] }), t.checkDate("limit").calendar(null, e), t.done(null, "change") }, nextYear: function () { o() || (a.year++, t.checkDate("limit").calendar(null, e), t.done(null, "change")) } } }, T.prototype.changeEvent = function () { var e = this; e.config; lay(e.elem).on("click", function (e) { lay.stope(e) }).on("mousedown", function (e) { lay.stope(e) }), lay.each(e.elemHeader, function (t, n) { lay(n[0]).on("click", function (n) { e.change(t).prevYear() }), lay(n[1]).on("click", function (n) { e.change(t).prevMonth() }), lay(n[2]).find("span").on("click", function (n) { var a = lay(this), i = a.attr("lay-ym"), r = a.attr("lay-type"); i && (i = i.split("-"), e.listYM[t] = [0 | i[0], 0 | i[1]], e.list(r, t), lay(e.footer).find("." + g).addClass(c)) }), lay(n[3]).on("click", function (n) { e.change(t).nextMonth() }), lay(n[4]).on("click", function (n) { e.change(t).nextYear() }) }), lay.each(e.table, function (t, n) { var a = lay(n).find("td"); a.on("click", function () { e.choose(lay(this), t) }) }), lay(e.footer).find("span").on("click", function () { var t = lay(this).attr("lay-type"); e.tool(this, t) }) }, T.prototype.isInput = function (e) { return /input|textarea/.test(e.tagName.toLocaleLowerCase()) }, T.prototype.events = function () { var e = this, t = e.config, n = function (n, a) { n.on(t.trigger, function () { a && (e.bindElem = this), e.render() }) }; t.elem[0] && !t.elem[0].eventHandler && (n(t.elem, "bind"), n(t.eventElem), t.elem[0].eventHandler = !0) }, r.that = {}, r.getThis = function (e) { var t = r.that[e]; return t || hint.error(e ? l + " instance with ID '" + e + "' not found" : "ID argument required"), t }, n.run = function (t) { t(document).on("mousedown", function (e) { if (i.thisId) { var n = r.getThis(i.thisId); if (n) { var a = n.config; e.target !== a.elem[0] && e.target !== a.eventElem[0] && e.target !== t(a.closeStop)[0] && n.remove() } } }).on("keydown", function (e) { if (i.thisId) { var n = r.getThis(i.thisId); n && 13 === e.keyCode && t("#" + n.elemID)[0] && n.elemID === T.thisElemDate && (e.preventDefault(), t(n.footer).find(f)[0].click()) } }), t(e).on("resize", function () { if (i.thisId) { var e = r.getThis(i.thisId); if (e) return !(!e.elem || !t(o)[0]) && void e.position() } }) }, i.render = function (e) { var t = new T(e); return r.call(t) }, i.parse = function (e, t, n) { return e = e || {}, "string" == typeof t && (t = r.formatArr(t)), t = (t || []).concat(), lay.each(t, function (a, i) { /yyyy|y/.test(i) ? t[a] = lay.digit(e.year, i.length) : /MM|M/.test(i) ? t[a] = lay.digit(e.month + (n || 0), i.length) : /dd|d/.test(i) ? t[a] = lay.digit(e.date, i.length) : /HH|H/.test(i) ? t[a] = lay.digit(e.hours, i.length) : /mm|m/.test(i) ? t[a] = lay.digit(e.minutes, i.length) : /ss|s/.test(i) && (t[a] = lay.digit(e.seconds, i.length)) }), t.join("") }, i.getEndDate = function (e, t) { var n = new Date; return n.setFullYear(t || n.getFullYear(), e || n.getMonth() + 1, 1), new Date(n.getTime() - 864e5).getDate() }, t ? (i.ready(), layui.define("lay", function (e) { i.path = layui.cache.dir, n.run(lay), e(l, i) })) : "function" == typeof define && define.amd ? define(function () { return n.run(lay), i }) : function () { i.ready(), n.run(e.lay), e.laydate = i }() }(window);
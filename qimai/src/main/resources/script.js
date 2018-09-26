function toCharCode(a) {
	var n = unescape("861831832863830866861836861862839831831839862863839830865834861863837837830830837839836861835833".replace(/8/g, "%u00"));
	a = a.split("");
	for (var e = a.length, t = n.length, i = ["c", "h", "a", "r", "C", "o", "d", "e", "A", "t"].join(""), s = 0; s < e; s++)
		a[s] = String.fromCharCode(a[s][i](0) ^ n[s % t][i](0));
	return a.join("");
}
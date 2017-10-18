$.fn.setForm = function (jsonValue) {
    var obj = this;
    var a = this.serializeArray();
    $.each(jsonValue, function (name, ival) {
            name = name.replace(/\$/g, "");
            if (ival && ival != "" && ival instanceof Object) {
                $.each(ival, function (n, ival2) {
                    var parentName = name;
                    setValue(parentName + '.' + n.replace(/\$/g, ""), ival2);
                })
            }
            else {
                setValue(name, ival);
            }

        }
    )

    function setValue(name, value) {
        var $oinput = obj.find("input[name='" + name + "']");
        if ($oinput.attr("type") == "checkbox") {
            if (value !== null) {
                var checkboxObj = $("[name='" + name + "']");
                var checkArray = value.split(";");
                for (var i = 0; i < checkboxObj.length; i++) {
                    for (var j = 0; j < checkArray.length; j++) {
                        if (checkboxObj[i].value == checkArray[j]) {
                            checkboxObj[i].click();
                        }
                    }
                }
            }
        }
        else if ($oinput.attr("type") == "radio") {
            $oinput.each(function () {
                var radioObj = $("[name='" + name + "']");
                for (var i = 0; i < radioObj.length; i++) {
                    if (radioObj[i].value == value) {
                        radioObj[i].click();
                    }
                }
            });
        }
        else if ($oinput.attr("type") == "textarea") {
            obj.find("[name='" + name + "']").html(value);
        }
        else {
            obj.find("[name='" + name + "']").val(value);
        }

        var $oselect = obj.find("select.select2[name='" + name + "']");
        if ($oselect) {
            $oselect.val(value).trigger("change");
        }

    }
}
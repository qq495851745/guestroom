/**
 * 辅助方法，动态生产树形菜单，属性菜单结构为无文件夹图标形式
 * 参数为json
 * option 自定义的一个名字 唯一性
 * treeId:树形菜单展示的地方
 * selectId:当前选择的项。
 * {option:"option",treeId:"",url:"",selectId:""}
 * @param json
 */
function btTree(json) {
    var $root = $("#" + json.treeId);//树形菜单的根
    $root.html("");
    $.ajax({
        url: json.url,
        type: "post",
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, obj) {
                var pid = json.option + obj.pid;
                var id = json.option + obj.id;
                var html = "<li btTree=\"" + id + "\" btpTree=\""+pid+"\"><a href='" + obj.href + "" + obj.id + "' target=\"ajax\" rel='" + obj.rel + "'>" + obj.name + "</a></li>";
                var len = $root.find("[btTree=" + pid + "]").size();//查找父亲
                if (len) {//查到
                    var size = $root.find("[btTree=" + pid + "]>ul").size();
                    if (size == 0)//没查到
                        $root.find("[btTree=" + pid + "]").append("<ul></ul>");
                    $root.find("[btTree=" + pid + "]>ul").append(html);
                } else {//没查到
                    len = $root.find("[btpTree=" + id + "]").size();//查找是否已经有该li的子标签
                    if(len==0)
                        $root.append(html);
                    else{//找到
                      $root.append($(html).append($(html).append("<ul></ul>").find("ul").append($root.find("[btpTree=" + id + "]"))));
                    }

                }

            });
            $root.jTree();
            //选中菜单
            if (!!json.selectId) {
                //选中菜单
                $root.find("li[bttree=" + json.selectId + "]>div").removeClass().addClass("selected");
            }
            $("#" + json.treeId + " a[target=ajax]").each(function () {
                $(this).click(function (event) {
                    var $this = $(this);
                    if ($this.hasClass('disabled') || $this.hasClass('buttonDisabled')) {
                        return false;
                    }

                    var rel = $this.attr("rel");
                    if (rel) {
                        var $rel = $("#" + rel);
                        var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
                        DWZ.debug(url);
                        if (!url.isFinishedTm()) {
                            alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
                            return false;
                        }

                        $rel.loadUrl(url, {}, function () {
                            $rel.find("[layoutH]").layoutH();
                        });
                    }

                    event.preventDefault();
                });
            });

        }
    });
}

/**
 *   修改添加，删除，编辑按钮的文字显示
 *   和点击事件
 *   {treeId:"",relId:""}
 */
function displayText(json) {
    if (!!json["relId"]) {
        //修改添加项目
        if (!!json["treeId"]) {
            var $sel = $("#" + json["treeId"]).find(".selected").find("a");
            var $add = $("#" + json["relId"]).find("a.add");
            if ($sel.size() == 1 && $add.size() == 1) {
                var text = $sel.text();
                $add.find("span").html("添加" + "<b style='color: green'>" + text + "</b>" + "的子项目");
            }
        }
        //修改删除标签
        $sel = $("#" + json["relId"]).find(".gridTbody").find("tr");

        $sel.on("click", function () {
            var id = $(this).attr("rel");//获取rel属性 就是id
            var $del = $("#" + json["relId"]).find("a.delete");
            var text = $del.attr("href");
                text = text.replace(/\/[^\/]+$/g, "/" + id);
                $del.attr("href", text); //修改href属性
                text = $(this).find("td:eq(1)").find("div").text();
                $del.find("span").html("删除" + "<b style='color: red'>" + text + "</b>");
            var $edit = $("#" + json["relId"]).find("a.edit");
                $edit.find("span").html("修改/查看" + "<b style='color: red'>" + text + "</b>");
            text = $edit.attr("href");
            text = text.replace(/\/[^\/]+$/g, "/" + id);
            $edit.attr("href", text); //修改href属性


        })
    }

}

/**
 * 1.room_room_level_lookup.html
 * 生成复选框
 * * 参数为json
 * option 自定义的一个名字 唯一性
 * treeId:树形菜单展示的地方
 * selectId:默认选中。
 * name:带回标识
 * {option:"option",treeId:"",url:"",selectId:""}
 * @param json
 */
function btTreeBox(json) {
    var $root = $("#" + json.treeId);//树形菜单的根
    $root.html("");
    $.ajax({
        url: json.url,
        type: "post",
        dataType: "json",
        async: false,
        success: function (data) {
            $.each(data, function (i, obj) {
                var pid = json.option + obj.pid;
                var id = json.option + obj.id;
                var value = "{\"id\":" + obj.id + ",\"name\":\"" + obj.name + "\"}";
                var html = "<li btTree=\"" + id + "\"><a tname='" + json.name + "' tvalue='" + value + "'>" + obj.name + "</a></li>";
                var len = $root.find("[btTree=" + pid + "]").size();//查找父亲
                if (len) {//查到
                    var size = $root.find("[btTree=" + pid + "]>ul").size();
                    if (size == 0)//没查到
                        $root.find("[btTree=" + pid + "]").append("<ul></ul>");
                    $root.find("[btTree=" + pid + "]>ul").append(html);
                } else {//没查到
                    $root.append(html);
                }

            });
        }
    });

    //$root.jTree();

}


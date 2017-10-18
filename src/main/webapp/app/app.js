/**
 * Created by wjtc8 on 2017/5/10.
 */
(function () {
    $(document).ready(function () {

        //第一次初始化的时候默认打开被激活的页面
        render($(".treeview-menu  > li.active"));
        //左侧菜单栏样式变化
        $(document).on("click", ".treeview-menu > li", function () {
            render($(this));
            return false;
        });

        function render(target) {
            var href = target.find("a").attr("href");
            $(".treeview-menu  > li.active").removeClass("active");
            $("section.content-header  > h1").text(target.find("a").text());
            $("ol.breadcrumb").find("li:last").text(target.find("a").text());
            $("ol.breadcrumb").find("li:first").html('<i class="fa fa-folder-open-o"></i>' + target.parent().parent().find("a>span:first").text());
            target.addClass("active");
            $('section.content').load(href, function () {
                //拼音组件
                $('.py[source]').each(function (index, Element) {
                    var pyElement = $(this);
                    var source = pyElement.attr("source");
                    $("#" + source).keyup(function () {
                        pyElement.val(pinyinUtil.getFirstLetter($(this).val()));
                    })
                })
            });

        }

    });


    $.extend(true, $.fn.dataTable.defaults, {
        "processing": true,
        "searching": false,
        "paging": true,
        "lengthChange": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Chinese.json"
        }
    });


}(jQuery))
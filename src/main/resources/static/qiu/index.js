layui.extend({
    qiu: 'lay/modules/qiu',
    validate: 'lay/modules/validate'
}).define(['qiu', 'conf', 'jquery'], function (exports) {
    layui.qiu.initPage();
    // 拓展jq函数
    var $ = layui.jquery;
    $.fn.serializeJson = function() {
        var serializeObj = {};
        $(this.serializeArray()).each(function(){
            serializeObj[this.name] = this.value;
        });
        return serializeObj;
    };
    console.log("\n %c qiucode-admin 1.0 %c https://gitee.com/zhenqi/qiucode-admin %c 如果该项目对您有帮助的话，还请点个star给予精神支持！🐤", "color: #fff; font-size: .84rem;background: #366ed8; padding:5px 0;", "font-size: .84rem;background: #fff; border: 2px solid #b0e0a8;border-left: none; padding:3px 0;"," font-size: .84rem;background: #fcf9ec; padding:5px 0;margin-left: 8px");
    exports('index', {});
});
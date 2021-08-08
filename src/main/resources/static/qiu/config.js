layui.define(function(exports) {
  exports('conf', {
    container: 'qiu',
    containerBody: 'qiu-body',
    v: '2.0',
    base: layui.cache.base,
    css: layui.cache.base + 'css/',
    views: layui.cache.base + 'views/',
    viewLoadBar: true,
    debug: layui.cache.debug,
    name: 'qiu',
    entry: '/index',
    engine: '',
    eventName: 'qiu-event',
    tableName: 'qiu',
    requestUrl: './'//'./'
  })
});

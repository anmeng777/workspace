//index.js
// 获取应用实例
var app = getApp()
Page({
  data: {
    motto: "hello world",
    userInfo: {}
  },
  // 事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    console.log('onLoad')
    var that = this
    // 调用应用实例的方法获取全局数据
    app.getUserInfo(function (userInfo) {
      // 更新数据
      that.setData({
        userInfo: userInfo
      })
    })
  },
  onShareAppMessage: function () {

    return {

      title: '小程序',

      desc: '测试',

      path: '/pages/index/index',

      imageUrl: 'http://img.hb.aicdn.com/d166ea58f81676521ab0a9c08bb229567c20ef22aa9a-tN6KPk_fw658'
    }

  }
})

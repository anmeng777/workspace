//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  getUserInfo: function (cb) {
    var that = this;
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      // 调用登录接口
      wx.login({
        success: function (res) {
        //发起网络请求  
          wx.request({
            url: "https://16u882035y.51mypc.cn/autoCheckin/miniProgram/getOpenid",
            data: {
              code: res.code,
            },
            header: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            method: 'POST',
            //服务端的回掉  
            success: function (result) {
              var data = result.data;
              if(data=="YES"){
                  wx.navigateTo({
                    url: '/pages/mima/mima'
                  })
                // wx.redirectTo({
                //   url: '/pages/finance/index'
                // })
              }
            }          
          })
          //获取个人信息
          wx.getUserInfo({
            success: function (result) {
              console.log(JSON.stringify(result.userInfo))
              globalData: {
                userInfo: result.userInfo
              }
            }
          })
        }
      });
    }
  },
  globalData: {
    userInfo: null
  }
})
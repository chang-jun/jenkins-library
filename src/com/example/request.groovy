#!/usr/bin/env groovy
package com.example

// http request
Object request(String method, String url, String body) {
    Object result = httpRequest(httpMode: method,
            acceptType: 'APPLICATION_JSON_UTF8',
            contentType: 'APPLICATION_JSON_UTF8',
            customHeaders: [
                [maskValue: false, name: 'access-token', value: 'abcdefghijklmnopqrst']
            ],
            consoleLogResponseBody: true,
            ignoreSslErrors: true,
            requestBody: body,
            url: url,
            quiet: true)
    println(result)

    return result
}

// 构建请求的 Body
String requestBody(String gitAppGroup,
                   String gitAppName,
                   String gitAppBranch,
                   String projectGroup,
                   String projectEnv,
                   String buildUser,
                   String buildMessage,
                   String buildUrl) {

    String requestBody = """{
      "gitAppGroup": "${gitAppGroup}",
      "gitAppName": "${gitAppName}",
      "gitAppBranch": "${gitAppBranch}",
      "projectGroup": "${projectGroup}",
      "projectEnv": "${projectEnv}",
      "buildUser": "${buildUser}",
      "buildMessage": "${buildMessage}",
      "buildUrl": "${buildUrl}",
    }"""

    return requestBody
                   }

// post request
void httpPost(String appGroup,
              String appName,
              String gitBranch,
              String projectGroup,
              String buildUser,
              String buildMessage,
              String buildUrl) {
    color.green('>>>>>>>>>>>> 推送信息 <<<<<<<<<<<<')

    String url = 'https://httpbin.org/post'
    String body = requestBody(appGroup, appName, gitBranch, projectGroup, buildUser, buildMessage, buildUrl)
    request('POST', url, body)
}

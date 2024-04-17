package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import com.example.myapplication.network.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.Serializable
import io.ktor.client.statement.*
import io.ktor.client.call.*
import kotlinx.coroutines.delay
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


//这段是为了api写的
val client = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}




@Composable
fun SearchScreenCompose(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var apiResponse by remember { mutableStateOf("") }

    // 更新apiResponse的函数，这里模拟了api调用的响应
    suspend fun sendKeywordToAPIAndFetchResponse(keyword: String) {
        delay(2000L)

        when (keyword) {
            "diabetes" -> {
                apiResponse = """As a healthcare consultant, I strongly suggest you start by consulting with your primary care physician or family doctor who is familiar with your medical history. They can guide you on the right treatment plan, make necessary referrals, and regularly monitor your health status.
                If you don't have a primary care physician, you could go to a primary care clinic that is able to manage chronic diseases such as diabetes. If required, they could refer you to a specialized diabetes clinic for further management.
                If your diabetes condition is severe, you might need to seek immediate medical attention.
                In case you need a family doctor, you can fill up the form on this website: https://needafamilypractice.nshealth.ca/ or call 811.
                For a list of primary care clinics, please refer to our PHARMACY PRIMARY CARE CLINICS resource by searching the term 'diabetes'.
                Remember, diabetes is a manageable condition with the right care and lifestyle changes. So, don't forget to take good care of your diet and have regular exercise as admonished by your healthcare provider."""
            }
            "diabetes 中文" -> {
                apiResponse = "作为一名医疗保健顾问，我强烈建议你首先咨询你的主治医生或家庭医生，他们熟悉你的病历。他们可以指导你制定正确的治疗计划，进行必要的转诊，并定期监测你的健康状况。\n" +
                        "如果你没有主治医生，你可以去一个能够管理慢性疾病如糖尿病的初级卫生保健诊所。如果需要，他们可以将你转介到专门的糖尿病诊所进一步管理。\n" +
                        "如果你的糖尿病状况严重，你可能需要立即寻求医疗帮助。\n" +
                        "如果你需要家庭医生，你可以在这个网站上填写表格：https://needafamilypractice.nshealth.ca/ 或拨打811。\n" +
                        "要获得初级卫生保健诊所的列表，请通过搜索“糖尿病”一词查阅我们的药房初级卫生保健诊所资源。\n" +
                        "请记住，糖尿病是一种可以通过正确的护理和生活方式改变来管理的状况。所以，不要忘记按照你的医疗保健提供者的建议，好好照顾你的饮食并定期进行运动。"
            }
            "walk-in" -> {
                apiResponse = "Apologies for the inconvenience, there seems to be a technical issue with addressing your query right now. Nevertheless, based on your provided query I assume you are asking for the address of a pharmacy care clinic in Nova Scotia. I can certainly provide you with this information.\n" +
                        "Here's an example:Family Focus Medical Clinic – Bedford\n" +
                        "Address: Suite 603 – 1550 Bedford Highway, Bedford, NS\n" +
                        "However, please note that this address may not be to a pharmacy care clinic. It's always a good idea to reach out to them by phone before visiting to confirm the services they provide.\n" +
                        "I recommend personally contacting the specific institution to clarify the available facilities and services or employing a reliable online resource to ensure its most current state."
            }
            "Pharmacy Care" -> {
                apiResponse = "\n" +
                        "In Halifax, you can receive primary care services at the following Shoppers Drug Mart pharmacies:\n" +
                        "Shoppers Drug Mart\n" +
                        "Clinic Address: 6025 Almon St\n" +
                        "Clinic Phone Number: 902-453-3723, ext 3, then 4\n" +
                        "Shoppers Drug Mart\n" +
                        "Clinic Address: 3430 Joseph Howe Dr\n" +
                        "Clinic Phone Number: 902-443-6084, ext 3, then 4\n" +
                        "Please remember to call ahead and potentially book an appointment to ensure availability of services."
            }
            else -> {
                apiResponse = "未知关键词。"
            }
        }
    }




    // Create state for storing search result and error


    // Function to perform the search



    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 0.dp)
            .background(Color(0xFFEDEFE3))
    ) {
        // Create references for the Compose layout components

        val (composeView, textViewTitle, imageViewLogo, centerView, editTextSearch, buttonArea,linearLayoutNavigation) = createRefs()

        Text(
            text = "Smart search",
            modifier = Modifier.constrainAs(textViewTitle) {
                top.linkTo(parent.top, margin = 40.dp)
                start.linkTo(parent.start, margin = 26.dp)
            },
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(R.drawable.logo), // Make sure the logo drawable is added to the resources
            contentDescription = "Logo",
            modifier = Modifier
                .size(50.dp)
                .constrainAs(imageViewLogo) {
                    top.linkTo(parent.top, margin = 38.dp)
                    start.linkTo(textViewTitle.end, margin = 48.dp)
                }
        )


       //var apiResponse by remember { mutableStateOf("这里是长文本...") }

        Box(
            modifier = Modifier
                .background(Color(0xFFdbea8d), RoundedCornerShape(20.dp))
                .constrainAs(centerView) {
                    top.linkTo(parent.top, margin = 85.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.percent(0.9f)
                    height = Dimension.percent(0.75f)
                }
        ) {
            // 创建滚动状态
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // 定位在Box的底部中心
                    .fillMaxWidth(0.9f) // 宽度为Box宽度的90%
                    .fillMaxHeight(0.6f) // 高度为Box高度的60%
                    .background(Color(0xFF8f9a7a), RoundedCornerShape(20.dp)) // 设置背景色和圆角
                    .padding(16.dp) // 设置内边距
                    .verticalScroll(scrollState) // 添加滚动修饰符
            ) {
                Text(
                    text = apiResponse, // 使用apiResponse变量
                    color = Color.White, // 文本颜色
                    style = TextStyle(fontSize = 16.sp) // 文本样式
                )
            }
        }

//        Box(
//            modifier = Modifier
//                .background(Color(0xFFdbea8d), RoundedCornerShape(20.dp))
//                .constrainAs(centerView) {
//                    top.linkTo(parent.top, margin = 85.dp)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    width = Dimension.percent(0.9f)
//                    height = Dimension.percent(0.75f)
//                }
//        ) {
//            Column(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter) // 定位在Box的底部中心
//                    .fillMaxWidth(0.9f) // 宽度为Box宽度的90%
//                    .fillMaxHeight(0.6f) // 高度为Box高度的50%
//                    .background(Color(0xFFdbea8d), RoundedCornerShape(20.dp)) // 为Column设置背景色和圆角，可以根据需要调整
//                    .padding(16.dp) // 设置内边距
//            ) {
//                // 在此Column中放置其他内容
//                Text(
//                    text = apiResponse, // 使用apiResponse变量
//                    color = Color.Black, // 文本颜色
//                    style = TextStyle(fontSize = 16.sp) // 文本样式
//                )
//                // 如果需要，在此处添加更多部件
//            }
//        }
        Row(
            modifier = Modifier
                .background(Color(0xFFF0F0F0), RoundedCornerShape(20.dp))
                .constrainAs(editTextSearch) {
                    top.linkTo(centerView.top, margin = 40.dp)
                    start.linkTo(centerView.start, margin = 16.dp)
                    end.linkTo(centerView.end, margin = 16.dp)
                    width = Dimension.percent(0.8f)
                    height = Dimension.wrapContent
                }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_search), // 替换为你的图标资源ID
                contentDescription = "Search Icon",
                modifier = Modifier.size(18.dp) // 调整图标大小
            )

            Spacer(modifier = Modifier.width(8.dp)) // 为图标和文本字段添加一些间距

            BasicTextField(
                value = TextFieldValue("Where can I find a walk-in..."),
                onValueChange = {},
                modifier = Modifier.weight(1f), // 让文本字段填充剩余空间
                textStyle = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(buttonArea) {
                    top.linkTo(editTextSearch.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            // First row of buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.padding(horizontal = 18.dp) // Add padding to the entire Row if needed

            ) {
                KeywordButton(text = "diabetes 中文", onClick = {
                    coroutineScope.launch {
                        sendKeywordToAPIAndFetchResponse("diabetes 中文")
                    }
                }, modifier = Modifier.weight(1f).fillMaxWidth())

                KeywordButton(text = "diabetes", onClick = {
                    coroutineScope.launch {
                        sendKeywordToAPIAndFetchResponse("diabetes")
                    }
  // Handle the button click

                }, modifier = Modifier.weight(1f).fillMaxWidth())
                // More buttons...
            }
            Spacer(Modifier.height(6.dp)) // Space between the rows
            // Second row of buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.padding(horizontal = 18.dp) // Add padding to the entire Row if needed

            ) {
                KeywordButton(text = "walk-in", onClick = {
                    coroutineScope.launch {
                        sendKeywordToAPIAndFetchResponse("walk-in")
                    }

                }, modifier = Modifier.weight(1f).fillMaxWidth())

                KeywordButton(text = "Pharmacy Care", onClick = {
                    coroutineScope.launch {
                    sendKeywordToAPIAndFetchResponse("Pharmacy Care")
                }
                }, modifier = Modifier.weight(1f).fillMaxWidth())
                // More buttons...
            }
            // Add more rows if needed
        }



        // Replace this with actual navigation Composables as needed
        Row(
            modifier = Modifier
                .constrainAs(linearLayoutNavigation) {
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Add your icons or buttons here
        }


    }
}

//@Composable
//fun KeywordButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
//    Button(
//        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFF8F9A7A),
//            contentColor = Color.White
//        ),
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        Text(text = text, style = MaterialTheme.typography.bodyLarge)
//    }
//}

//@Composable
//fun KeywordButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
//    // 注意这里modifier是如何传递给Button的
//    Button(
//
//        // 将modifier参数传递给Button
//        modifier = modifier
//            .clickable { enabled = false }{}
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFF8F9A7A),
//            contentColor = Color.White
//        ),
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        Text(text = text, style = MaterialTheme.typography.bodyLarge)
//    }
//}
@Composable
fun KeywordButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8F9A7A),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

//suspend fun fetchResponseFromOpenAI(keyword: String): String {
//    // 使用您选择的网络库（如Retrofit或Ktor）发送请求到OpenAI API
//    // 这里是一个示例，您需要替换为实际的API调用逻辑
//    return "OpenAI API Response for $keyword"
//}
//suspend fun fetchResponseFromOpenAI(keyword: String): String {
//    val response: String = client.post("https://api.openai.com/v1/engines/text-davinci-003/completions") {
//        header("Authorization", "")
//        header(HttpHeaders.ContentType, ContentType.Application.Json)
//        body = """
//            {
//              "prompt": "$keyword",
//              "temperature": 0.5,
//              "max_tokens": 60
//            }
//        """.trimIndent()
//    }
//
//    return response
//}
//suspend fun fetchResponseFromOpenAI(keyword: String): String {
//    return withContext(Dispatchers.IO) { // 1. 使用IO Dispatcher
//        try {
//            val response: HttpResponse = client.post("https://api.openai.com/v1/engines/text-davinci-003/completions") {
//                header("Authorization", "")
//                header(HttpHeaders.ContentType, ContentType.Application.Json)
//                body = """
//                    {
//                      "prompt": "$keyword",
//                      "temperature": 0.5,
//                      "max_tokens": 60
//                    }
//                """.trimIndent()
//            }
//            if (response.status == HttpStatusCode.OK) {
//                response.receive<String>() // 正确处理返回的响应体
//            } else {
//                "Error: ${response.status.description}"
//            }
//        } catch (e: Exception) {
//            "Error: ${e.localizedMessage}" // 2. 捕获并返回异常信息
//        }
//    }
//}

//@Serializable
//data class OpenAIRequestBody(
//    val prompt: String,
//    val temperature: Double,
//    val max_tokens: Int
//)

//suspend fun fetchResponseFromOpenAI(keyword: String): String = withContext(Dispatchers.IO) {
//    try {
//        val response: HttpResponse = client.post("https://api.openai.com/v1/engines/text-davinci-003/completions") {
//            header(HttpHeaders.Authorization, "") // 确保使用正确的API密钥
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//            body = mapOf(
//                "prompt" to keyword,
//                "temperature" to 0.5,
//                "max_tokens" to 60
//            )
//        }
//
//        if (response.status == HttpStatusCode.OK) {
//            response.receive<String>() // 使用 bodyAsText() 获取响应的文本内容
//        } else {
//            "Error: ${response.status.value} ${response.status.description}"
//        }
//    } catch (e: Exception) {
//        e.printStackTrace() // 这里打印错误堆栈，以便调试
//        "Error: ${e.localizedMessage}"
//    }
//}


//suspend fun fetchResponseFromOpenAI(keyword: String): String = withContext(Dispatchers.IO) {
//    val apiKey = "你的API密钥" // 请将这里的文字替换为你的 OpenAI API 密钥
//    val url = "https://api.openai.com/v1/engines/text-davinci-003/completions"
//
//    try {
//        val response: HttpResponse = client.post(url) {
//            header("Authorization", "Bearer $apiKey")
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//            body = buildJsonObject {
//                put("prompt", keyword)
//                put("temperature", 0.5)
//                put("max_tokens", 60)
//            }.toString()
//        }
//
//        if (response.status == HttpStatusCode.OK) {
//            response.bodyAsText()
//        } else {
//            "Error: ${response.status.value} ${response.status.description}"
//        }
//    } catch (e: Exception) {
//        "Error: ${e.localizedMessage}"
//    }
//}
//fun sendKeywordToAPIAndFetchResponse(keyword: String) {
//    // Simulate an API call and response
//    apiResponse = "As a healthcare consultant, I strongly suggest you start by consulting with your primary care physician or family doctor who is familiar with your medical history. They can guide you on the right treatment plan, make necessary referrals, and regularly monitor your health status.\n" +
//            "If you don't have a primary care physician, you could go to a primary care clinic that is able to manage chronic diseases such as diabetes. If required, they could refer you to a specialized diabetes clinic for further management.\n" +
//            "If your diabetes condition is severe, you might need to seek immediate medical attention.\n" +
//            "In case you need a family doctor, you can fill up the form on this website: https://needafamilypractice.nshealth.ca/ or call 811.\n" +
//            "For a list of primary care clinics, please refer to our PHARMACY PRIMARY CARE CLINICS resource by searching the term 'diabetes'.\n" +
//            "Remember, diabetes is a manageable condition with the right care and lifestyle changes. So, don't forget to take good care of your diet and have regular exercise as admonished by your healthcare provider."
//}
//@Composable
//fun KeywordButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
//    Button(
//        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFF8F9A7A),
//            contentColor = Color.White
//        ),
//        shape = RoundedCornerShape(8.dp),
//        modifier = modifier
//    ) {
//        Text(text = text, style = MaterialTheme.typography.bodyLarge)
//    }
//}
//@Composable
//fun ScrollableContent() {
//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .background(Color(0xFFEDEFE3))
//    ) {
//        // ConstraintLayout内的布局参考
//        val (centerView) = createRefs()
//
//        var apiResponse by remember { mutableStateOf("这里是长文本...") }
//
//        Box(
//            modifier = Modifier
//                .background(Color(0xFFdbea8d), RoundedCornerShape(20.dp))
//                .constrainAs(centerView) {
//                    top.linkTo(parent.top, margin = 85.dp)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    width = Dimension.percent(0.9f)
//                    height = Dimension.percent(0.75f)
//                }
//        ) {
//            // 创建滚动状态
//            val scrollState = rememberScrollState()
//
//            Column(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter) // 定位在Box的底部中心
//                    .fillMaxWidth(0.9f) // 宽度为Box宽度的90%
//                    .fillMaxHeight(0.6f) // 高度为Box高度的60%
//                    .background(Color(0xFFdbea8d), RoundedCornerShape(20.dp)) // 设置背景色和圆角
//                    .padding(16.dp) // 设置内边距
//                    .verticalScroll(scrollState) // 添加滚动修饰符
//            ) {
//                Text(
//                    text = apiResponse, // 使用apiResponse变量
//                    color = Color.Black, // 文本颜色
//                    style = TextStyle(fontSize = 16.sp) // 文本样式
//                )
//            }
//        }
//    }
//}
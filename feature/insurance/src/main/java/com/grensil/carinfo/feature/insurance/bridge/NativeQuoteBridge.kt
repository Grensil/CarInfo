package com.grensil.carinfo.feature.insurance.bridge

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast

class NativeQuoteBridge(
    private val context: Context,
    private val onQuoteSubmitted: (quote: QuoteData) -> Unit = {},
) {
    @JavascriptInterface
    fun sendQuote(jsonData: String) {
        try {
            Log.d("NativeQuoteBridge", "Quote received: $jsonData")
            // JSON 파싱 (간단한 예시, 실제로는 JSON 라이브러리 사용)
            val quote = parseQuoteJson(jsonData)
            Toast.makeText(context, "견적이 수신되었습니다: ${quote.totalPrice}원", Toast.LENGTH_SHORT).show()
            onQuoteSubmitted(quote)
        } catch (e: Exception) {
            Log.e("NativeQuoteBridge", "Error processing quote", e)
            Toast.makeText(context, "견적 처리 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun openCamera(params: String) {
        Log.d("NativeQuoteBridge", "Camera requested: $params")
        Toast.makeText(context, "카메라 기능 (구현 필요)", Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun getLocation(params: String) {
        Log.d("NativeQuoteBridge", "Location requested: $params")
        // 위치 권한 확인 후 위치 반환
        Toast.makeText(context, "위치 기능 (구현 필요)", Toast.LENGTH_SHORT).show()
    }

    private fun parseQuoteJson(json: String): QuoteData {
        // 실제로는 JSON 파서 사용 (kotlinx.serialization 등)
        return QuoteData(
            quoteId = "QUOTE_${System.currentTimeMillis()}",
            totalPrice = 5000000, // Mock data
        )
    }
}

data class QuoteData(
    val quoteId: String,
    val totalPrice: Int,
)

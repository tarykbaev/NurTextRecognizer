package kg.nurtelecom.nur_text_recognizer.analyzer

import kg.nurtelecom.nur_text_recognizer.recognizer.BaseTextRecognizer
import kg.nurtelecom.nur_text_recognizer.recognizer.PassportMrzRecognizer

class PassportImageAnalyzer(listener: ImageAnalyzerCallback) : BaseImageAnalyzer(listener) {

    override val recognizer: BaseTextRecognizer by lazy {
        PassportMrzRecognizer(this)
    }
}
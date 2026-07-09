package io.unthrottled.doki.service

import com.intellij.openapi.editor.colors.EditorColorsManager
import io.unthrottled.doki.config.ThemeConfig
import io.unthrottled.doki.themes.ThemeManager
import io.unthrottled.doki.util.Logging
import io.unthrottled.doki.util.logger
import io.unthrottled.doki.util.runSafely

object CustomFontSizeService : Logging {
  fun applyCustomFontSize() {
    ThemeManager.instance.currentTheme
      .filter { ThemeConfig.instance.isGlobalFontSize }
      .ifPresent {
        runSafely({
          EditorColorsManager.getInstance().schemeForCurrentUITheme
            .editorFontSize = ThemeConfig.instance.customFontSize
        }) {
          logger().warn("Unable to apply custom font size", it)
        }
      }
  }
}

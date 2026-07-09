package io.unthrottled.doki.service

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.colors.EditorColorsManager
import io.unthrottled.doki.config.ThemeConfig
import io.unthrottled.doki.settings.actors.ConsoleFontActor
import io.unthrottled.doki.themes.ThemeManager
import io.unthrottled.doki.util.Logging
import io.unthrottled.doki.util.logger
import io.unthrottled.doki.util.runSafely

object ConsoleFontService : Logging {
  fun applyConsoleFont() {
    ThemeManager.instance.currentTheme
      .filter { ThemeConfig.instance.isOverrideConsoleFont }
      .ifPresent {
        runSafely({
          EditorColorsManager.getInstance().schemeForCurrentUITheme
            .consoleFontName = ThemeConfig.instance.consoleFontName
        }) {
          logger().warn("Unable to apply console font", it)
        }
        ApplicationManager.getApplication().invokeLater {
          ConsoleFontActor.refreshConsole()
        }
      }
  }
}

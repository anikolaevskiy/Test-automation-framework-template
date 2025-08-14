package com.example.aqa.configuration.driver.playwright;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Spring configuration wiring Playwright Chromium {@link Page} instances.
 * <p>
 * The configuration is activated when the {@code playwright} profile is enabled.
 * Additional profiles {@code chrome-local} or {@code chrome-remote} decide
 * whether the browser runs locally or connects to a remote endpoint.
 */
@Profile({"playwright"})
@Configuration
@EnableConfigurationProperties(PlaywrightProperties.class)
public class PlaywrightChromiumConfiguration {

    /**
     * Provides default {@link BrowserType.LaunchOptions} for Chromium sessions.
     *
     * @return configured launch options
     */
    @Bean
    public BrowserType.LaunchOptions chromiumLaunchOptions() {
        return new BrowserType.LaunchOptions().setHeadless(false);
    }

    /**
     * Creates a {@link Page} connected to a remote Chromium instance.
     *
     * @param properties Playwright connection properties
     * @return remote page
     * @throws URISyntaxException if the endpoint URI is invalid
     */
    @Profile("chrome-remote")
    @Bean(destroyMethod = "close")
    public Page remoteChromiumPage(PlaywrightProperties properties) throws URISyntaxException {
        var playwright = Playwright.create();
        var chromium = playwright.chromium();
        var browser = chromium.connectOverCDP(properties.getGridHost());
        var page = browser.newPage();
        page.navigate(properties.getAppHost());
        return page;

    }

    /**
     * Creates a local {@link Page} using Chromium.
     *
     * @param properties Playwright connection properties
     * @param options    launch options
     * @return local page
     */
    @Profile("chrome-local")
    @Bean(destroyMethod = "close")
    public Page localChromiumPage(PlaywrightProperties properties, BrowserType.LaunchOptions options) {
        var playwright = Playwright.create();
        var chromium = playwright.chromium();
        var browser = chromium.launch(options);
        var page = browser.newPage();
        page.navigate(properties.getAppHost());
        return page;
    }
}


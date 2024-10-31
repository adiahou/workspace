const { defineConfig } = require("cypress");

module.exports = defineConfig({
  env: {
    login_url: '/login',
    products_url: '/products',
    http_proxy: "http://127.0.0.1:26001",
    https_proxy: "http://127.0.0.1:26001",
    chromeWebSecurity: false,
    userDataDir: "/Users/beiouyexiasha/Library/Application Support/Google/Chrome/Default"
  },
  numTestsKeptInMemory: 5,
  reporter: "mochawesome",
  reporterOptions: {
    "reportDir": "cypress/results",
    "overwrite": false,
    "html": true,
    "json": true
  },
  retries: {
    // Configure retry attempts for `cypress run`
    // Default is 0
    runMode: 2,
    // Configure retry attempts for `cypress open`
    // Default is 0
    openMode: 0
  },
  e2e: {
    // baseUrl: '',
    setupNodeEvents(on, config) {
      on('before:run', (details) => {
        /* ... */
      }),
      on('after:run', (results) => {
        /* ... */
      })
    }
  }
});

const { loadConfigFromFile, mergeConfig } = require("vite");
const path = require("path");

module.exports = ({
  stories: [
    '../src/storybook/stories/**/*.stories.mdx',
    '../src/storybook/stories/**/*.stories.@(js|jsx|ts|tsx)',
  ],
  addons: [
    '@storybook/addon-links',
    '@storybook/addon-essentials',
  ],
  framework: '@storybook/react',
  core: {
    builder: 'storybook-builder-vite',
  },
  viteFinal: async (config, { configType }) => {
    const { config: userConfig } = await loadConfigFromFile(
      path.resolve(__dirname, "../vite.config.ts")
    );

    return mergeConfig(config, {
      ...userConfig,
      // manually specify plugins to avoid conflict
      plugins: [],
      resolve: {
        alias: {
          '~': path.resolve(__dirname, '../src'),
          '@nara.drama/address-book': path.resolve(__dirname, '../src/comp'),
        }
      },
      server: {
        proxy: {
          // '/api/address-book': {
          //   target: 'http://localhost:9093',
          //   rewrite: path => path.replace('/api/address-book', ''),
          //   crossOrigin: true,
          //   configure: proxy => proxy.on('proxyReq', proxy => console.log(`-> ${proxy.protocol}//${proxy.host}${proxy.port}${proxy.path}`)),
          // },
          '/api': {
            target: 'http://demo.dev.naraway.io',
            rewrite: path => path.replace('/api', '/api'),
            crossOrigin: true,
            configure: proxy => proxy.on('proxyReq', proxy => console.log(`-> ${proxy.protocol}//${proxy.host}${proxy.port}${proxy.path}`)),
          },
        },
      },
    });
  },
});

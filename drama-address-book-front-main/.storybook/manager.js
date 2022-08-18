import {addons} from '@storybook/addons';
import {create} from '@storybook/theming';

addons.setConfig({
  theme: create({
    base: 'light',
    brandTitle: 'Nara Way',
    brandUrl: 'http://nextree.io',
    brandImage: 'http://demo.dev.naraway.io/townhall/images/logo-nara-red.png',
    brandTarget: '_self',
  }),
});

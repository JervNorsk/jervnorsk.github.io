import type { Preview } from '@storybook/nextjs-vite'

import "./preview.css";

import { MINIMAL_VIEWPORTS, INITIAL_VIEWPORTS } from 'storybook/viewport';

const preview: Preview = {
  parameters: {
    controls: {
      matchers: {
       color: /(background|color)$/i,
       date: /Date$/i,
      },
    },
    viewport: {
      options: {
        ...MINIMAL_VIEWPORTS,
        ...INITIAL_VIEWPORTS
      }
    },
    // nextjs: {
    //   appDirectory: true,
    // },
  },
  decorators: [
    (Story, context) => {
      const currentBg = context.globals.backgrounds?.value;
      return (
        <div id="app" className={currentBg}>
          <Story />
        </div>
      );
    },
  ],
};

export default preview;

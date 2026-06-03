import type { Preview } from '@storybook/nextjs-vite'

//@ts-ignore
// import "../src/app/globals.css";
//@ts-ignore
import "./preview.css";

const preview: Preview = {
  parameters: {
    controls: {
      matchers: {
       color: /(background|color)$/i,
       date: /Date$/i,
      },
    },
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

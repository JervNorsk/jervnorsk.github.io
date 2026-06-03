import type { Meta, StoryObj } from '@storybook/nextjs-vite';
import Home from './page';

const meta = {
  title: 'Pages/Home',
  component: Home,
  parameters: {
    layout: 'fullscreen',
  },
} satisfies Meta<typeof Home>;

export default meta;
type Story = StoryObj<typeof meta>;

/**
 * Scenario Base: Verifica il rendering della pagina con i parametri di default.
 * Utilizza i decorator globali definiti in preview.tsx per iniettare le classi del tema scuro.
 */
export const Default: Story = {};

// export const Mobile: Story = {
//   parameters: {
//     viewport: {
//       defaultViewport: 'sm',
//     },
//   },
// };
//
// export const DesktopXL: Story = {
//   parameters: {
//     viewport: {
//       defaultViewport: 'xl',
//     },
//   },
// };

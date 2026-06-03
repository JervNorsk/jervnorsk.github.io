import type { Meta, StoryObj } from '@storybook/nextjs-vite';

import { BlueprintGrid } from './blueprint-grid';

const meta = {
  title: 'Components/UI/Blueprint/Grid',
  component: BlueprintGrid,
  argTypes: {
    gridSize: {
      control: { type: "number", min: 8, max: 128, step: 4 },
    },
    opacity: {
      control: { type: "number", min: 0, max: 0.3, step: 0.01 },
    },
  },
  parameters: {
    layout: 'fullscreen',
  },
} satisfies Meta<typeof BlueprintGrid>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};


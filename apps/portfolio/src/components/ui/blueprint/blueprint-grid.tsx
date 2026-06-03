interface BlueprintGridProps {
  gridSize?: number;
  opacity?: number;
  className?: string;
}

export function BlueprintGrid(
  {
    gridSize = 32,
    opacity = 0.03,
    className = ""
  }: BlueprintGridProps
) {
  return (
    <div aria-hidden="true"
         className={`w-full h-full`}
         style={{
           "--grid-size": `${gridSize}px`,
           "--grid-opacity": opacity,
           backgroundImage: `
              linear-gradient(to right, currentColor 1px, transparent 1px),
              linear-gradient(to bottom, currentColor 1px, transparent 1px)
            `,
           backgroundSize: "var(--grid-size) var(--grid-size)",
           opacity: "var(--grid-opacity)",
         } as React.CSSProperties}
    />
  );
}

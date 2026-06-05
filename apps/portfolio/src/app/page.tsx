import {BlueprintGrid} from "@/components/ui/blueprint/blueprint-grid";

export default function Home() {
  return (
    <main className="relative flex-1 h-full border-8 border-accent-foreground overflow-hidden">
      <div id="background" className="absolute inset-0 pointer-events-none select-none">
        <BlueprintGrid className=""/>
      </div>
      <div id="parallax" className="absolute inset-0 pointer-events-none select-none">

      </div>
      <div id="foreground" className="relative z-20 p-10">

      </div>
    </main>
  );
}

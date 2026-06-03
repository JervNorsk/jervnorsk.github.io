import type {Metadata} from "next";
// import {JetBrains_Mono, Source_Sans_3} from "next/font/google";
import {ThemeProvider} from "next-themes";
import {cn} from "@/lib/utils";

import "./globals.css";

// const mono = JetBrains_Mono({
//   variable: '--font-mono',
//   subsets: ['latin']
// });
//
// const sans = Source_Sans_3({
//   variable: "--font-sans",
//   subsets: ["latin"],
// });

export const metadata: Metadata = {
  title: "JervNorsk | Portfolio",
  description: "Welcome to my portfolio, it's a showcase of my actual skills",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html
      lang="en"
      suppressHydrationWarning
      className={cn([
        // mono.variable,
        // sans.variable
      ])}
    >
    <body id="app" className="flex flex-col">
    <ThemeProvider
      attribute="class"
      defaultTheme="system"
      enableSystem
      disableTransitionOnChange
    >
      {children}
    </ThemeProvider>
    </body>
    </html>
  );
}

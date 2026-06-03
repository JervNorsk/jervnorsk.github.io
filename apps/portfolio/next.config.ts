import type {NextConfig} from "next";

const nextConfig: NextConfig = {
  distDir: 'build/html',
  output: "export",

  images: {
    unoptimized: true,
  },
};

export default nextConfig;

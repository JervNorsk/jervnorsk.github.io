import type {NextConfig} from "next";

const nextConfig: NextConfig = {
  distDir: 'build/nextjs',
  output: "export",

  images: {
    unoptimized: true,
  },
};

export default nextConfig;

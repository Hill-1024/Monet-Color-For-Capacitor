// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AndroidDynamicColor",
    platforms: [.iOS(.v15)],
    products: [
        .library(
            name: "AndroidDynamicColor",
            targets: ["MonetColorPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "8.0.0")
    ],
    targets: [
        .target(
            name: "MonetColorPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/MonetColorPlugin"),
        .testTarget(
            name: "MonetColorPluginTests",
            dependencies: ["MonetColorPlugin"],
            path: "ios/Tests/MonetColorPluginTests")
    ]
)
import Foundation

@objc public class MonetColor: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}

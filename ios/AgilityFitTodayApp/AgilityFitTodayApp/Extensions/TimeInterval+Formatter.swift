//
//  TimeInterval+Formatter.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation

extension TimeInterval {
    var hourMinuteSecondMS: String {
        String(format:"%02d:%02d:%02d.%02d", hour, minute, second, millisecond)
    }
    var minuteSecond: String {
        String(format:"%02d:%02d", minute, second)
    }
    var hour: Int {
        Int((self/3600).truncatingRemainder(dividingBy: 3600))
    }
    var minute: Int {
        Int((self/60).truncatingRemainder(dividingBy: 60))
    }
    var second: Int {
        Int(truncatingRemainder(dividingBy: 60))
    }
    var millisecond: Int {
        Int((self*1000).truncatingRemainder(dividingBy: 1000))
    }
}

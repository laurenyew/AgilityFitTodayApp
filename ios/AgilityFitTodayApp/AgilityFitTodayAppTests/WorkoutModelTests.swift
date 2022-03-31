//
//  AgilityFitTodayAppTests.swift
//  AgilityFitTodayAppTests
//
//  Created by laurenyew on 12/29/21.
//

import XCTest
@testable import AgilityFitTodayApp

class WorkoutModelTests: XCTestCase {
    func testWorkoutItemEstimatedTime() throws {
        // Setup
        let workoutItem = WorkoutItem(quantity: 1, itemBase: BasicWorkoutItems.Stretch.toWorkoutItemBase())
        let workoutItem2 = WorkoutItem(quantity: 2, itemBase: BasicWorkoutItems.Stretch.toWorkoutItemBase())
        
        // Exercise
        let result = workoutItem.estimatedTimeInSecs()
        let resultString = workoutItem.estimatedTimeFormattedString()
        let result2 = workoutItem2.estimatedTimeInSecs()
        let resultString2 = workoutItem2.estimatedTimeFormattedString()
        
        // Verify
        XCTAssertEqual(5 * 60, result)
        XCTAssertEqual("05:00", resultString)
        XCTAssertEqual(5 * 60 * 2, result2)
        XCTAssertEqual("10:00", resultString2)
    }
    
    func testWorkoutSequenceEstimatedTime() throws {
        // Setup
        let workoutItem1 = WorkoutItem(quantity: 1, itemBase: BasicWorkoutItems.Stretch.toWorkoutItemBase())
        let workoutItem2 = WorkoutItem(quantity: 2, itemBase: BasicWorkoutItems.Squats.toWorkoutItemBase())
        let workoutSequence = WorkoutSequence(name: "Test", description: "Testing...", workoutItems: [workoutItem1, workoutItem2], workoutType: .Stretch)
        
        // Exercise
        let result = workoutSequence.estimatedTimeInSecs()
        let resultString = workoutSequence.estimatedTimeFormattedString()
        
        // Verify
        XCTAssertEqual(7 * 60, result)
        XCTAssertEqual("07:00", resultString)
    }

    
//    override func setUpWithError() throws {
//        // Put setup code here. This method is called before the invocation of each test method in the class.
//    }
//
//    override func tearDownWithError() throws {
//        // Put teardown code here. This method is called after the invocation of each test method in the class.
//    }
//
//    func testPerformanceExample() throws {
//        // This is an example of a performance test case.
//        self.measure {
//            // Put the code you want to measure the time of here.
//        }
//    }

}

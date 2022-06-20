//
//  AgilityFitTodayApp.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 12/29/21.
//

import SwiftUI

@main
struct AgilityFitTodayApp: App {
    let workoutRepository = AppEnvironment.current.workoutRepository
    
    var body: some Scene {
        WindowGroup {
            MainView()
            // TODO: Fix create workout view
//            CreateWorkoutView()
//                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}

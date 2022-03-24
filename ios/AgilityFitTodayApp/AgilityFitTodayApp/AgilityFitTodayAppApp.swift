//
//  AgilityFitTodayApp.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 12/29/21.
//

import SwiftUI

@main
struct AgilityFitTodayApp: App {
    let persistenceController = PersistenceController.shared
    
    

    var body: some Scene {
        WindowGroup {
            HomeView()
            // TODO: Fix create workout view
//            CreateWorkoutView()
//                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}

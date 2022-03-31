//
//  SelectWorkoutView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation
import SwiftUI

struct SelectWorkoutView : View {
    var body: some View {
        NavigationView {
            VStack {
                Text("Select your Workout")
            }
            .navigationTitle("Select a Workout")
            .navigationBarTitleDisplayMode(.large)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: CreateWorkoutView()) {
                        Text("+")
                    }
                }
            }
        }
    }
}

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
        VStack {
            Text("Select your Workout")
        }
        .navigationTitle("Select a Workout")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                NavigationLink(destination: CreateWorkoutView()) {
                    Text("+")
                }
            }
        }
    }
}


struct SelectWorkoutView_Previews: PreviewProvider {
    static var previews: some View {
        SelectWorkoutView()
    }
}

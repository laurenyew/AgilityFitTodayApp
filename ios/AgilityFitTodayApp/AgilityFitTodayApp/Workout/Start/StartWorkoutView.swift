//
//  StartWorkoutView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation
import SwiftUI

struct StartWorkoutView : View {
    @ObservedObject var viewModel : StartWorkoutViewModel
    
    init(sequenceId: UUID){
        viewModel = StartWorkoutViewModel(sequenceID: sequenceId)
    }
    
    var body: some View {
        VStack {
            Text("Start your Workout")
        }
        .navigationTitle("Start Workout")
    }
}

struct StartWorkoutView_Previews: PreviewProvider {
    static var previews: some View {
        StartWorkoutView(sequenceId: UUID.init())
    }
}

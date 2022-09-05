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
        let workoutSequence = viewModel.workoutSequence
        VStack {
            if(workoutSequence != nil){
                StartWorkoutHeaderView(
                    title: workoutSequence?.name,
                    description: workoutSequence?.description,
                    estimatedTimeFormattedString: workoutSequence?.estimatedTimeFormattedString())
            } else {
                Text("Loading..")
            }
        }
        .navigationTitle("Start Workout")
    }
}

struct StartWorkoutView_Previews: PreviewProvider {
    static var previews: some View {
        StartWorkoutView(sequenceId: UUID.init())
    }
}

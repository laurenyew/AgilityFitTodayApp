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
        
        VStack(alignment: .leading, spacing: 0) {
            if let workoutSequence = workoutSequence {
                StartWorkoutHeaderView(
                    title: workoutSequence.name,
                    description: workoutSequence.description,
                    estimatedTimeFormattedString: workoutSequence.estimatedTimeFormattedString())
                ScrollView {
                    VStack(spacing: 0){
                        ForEach(workoutSequence.workoutItems) { item in
                            StartWorkoutRowView(
                                quantity: item.quantity,
                                name: item.itemBase.name,
                                estimatedFormattedTimeString: item.estimatedTimeFormattedString())
                        }
                    }
                }
            } else {
                Text("Loading..")
            }
            Spacer()
        }
        .navigationTitle("Start Workout")
    }
}

struct StartWorkoutView_Previews: PreviewProvider {
    static var previews: some View {
        StartWorkoutView(sequenceId: UUID.init())
    }
}

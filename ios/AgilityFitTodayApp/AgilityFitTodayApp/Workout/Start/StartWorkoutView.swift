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
        let selectedIndex = viewModel.currentWorkoutItemIndex ?? 0
        ZStack {
            VStack(alignment: .leading, spacing: 0) {
                if let workoutSequence = workoutSequence {
                    StartWorkoutHeaderView(
                        title: workoutSequence.name,
                        description: workoutSequence.description,
                        estimatedTimeLeftString: viewModel.estimatedTimeLeft ?? "N/A")
                    ScrollView {
                        VStack(spacing: 0){
                            ForEach(Array(workoutSequence.workoutItems.enumerated()), id: \.element) { index, item in
                                StartWorkoutRowView(
                                    quantity: item.quantity,
                                    name: item.itemBase.name,
                                    estimatedFormattedTimeString: item.estimatedTimeFormattedString(),
                                    isExecuting: index == selectedIndex,
                                    hasExecuted: index < selectedIndex
                                )
                            }
                        }
                    }
                } else {
                    Text("Loading..")
                }
                Spacer()
            }
            if workoutSequence != nil {
                StartWorkoutButton(currentState: .Paused) {
                    // TODO: Hook up workout button with view model.
                }
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

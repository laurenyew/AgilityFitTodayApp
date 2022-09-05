//
//  StartWorkoutButton.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 9/4/22.
//

import Foundation
import SwiftUI

struct StartWorkoutButton : View {
    let currentState: WorkoutExecutionButtonState
    let action: () -> Void
    
    var body: some View {
        VStack {
            Spacer()
            HStack {
                Spacer()
                Button(action: {
                    self.action()
                }, label: {
                    Text(label())
                        .font(.system(.largeTitle))
                        .frame(width: 70, height: 70)
                        .foregroundColor(Color.white)
                })
                .background(Color.blue)
                .cornerRadius(38.5)
                .padding()
                .shadow(color: Color.black.opacity(0.3),
                        radius: 3,
                        x: 3,
                        y: 3)
            }
        }
        .padding()
    }
    
    private func label() -> String {
        switch(self.currentState){
        case .Paused: return "▶︎"
        case .InProgress: return "◼︎"
        }
    }
}

struct StartWorkoutButton_Previews: PreviewProvider {
    static var previews: some View {
        StartWorkoutButton(currentState: .Paused) {
            // Do nothing
        }
    }
}

enum WorkoutExecutionButtonState {
    case Paused
    case InProgress
}

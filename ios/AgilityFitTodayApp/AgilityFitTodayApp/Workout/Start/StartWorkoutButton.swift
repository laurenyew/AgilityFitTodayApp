//
//  StartWorkoutButton.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 9/4/22.
//

import Foundation
import SwiftUI

struct StartWorkoutButton : View {
    let currentState: WorkoutExecutionState
    let action: () -> Void
    
    var body: some View {
        VStack {
            Spacer()
            HStack {
                Spacer()
                Button(action: {
                    self.action()
                }, label: {
                    if currentState != .Completed && currentState != .Cancelled {
                        Text(label())
                            .font(.system(.largeTitle))
                            .frame(width: 70, height: 70)
                            .foregroundColor(Color.white)
                    }
                    else {
                        Text(label())
                            .font(.system(.largeTitle))
                            .foregroundColor(Color.white)
                            .padding()
                    }
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
        case .NotStarted: return "▶︎"
        case .InProgress: return "◼︎"
        case .Stopped:
            return "▶︎"
        case .Cancelled:
            return "Restart"
        case .Completed:
            return "Restart"
        }
    }
}

struct StartWorkoutButton_Previews: PreviewProvider {
    static var previews: some View {
        StartWorkoutButton(currentState: .NotStarted) {
            // Do nothing
        }
    }
}

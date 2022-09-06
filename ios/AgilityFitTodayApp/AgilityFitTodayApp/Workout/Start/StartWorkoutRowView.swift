//
//  StartWorkoutRowView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 9/4/22.
//

import Foundation
import SwiftUI

struct StartWorkoutRowView : View {
    let quantity: Int
    let name: String
    let estimatedFormattedTimeString: String
    let isExecuting: Bool
    let hasExecuted: Bool
    
    var body: some View {
        let backgroundColor: Color = backgroundColor()
        HStack(alignment:.center) {
            if(quantity > 1){
                Text(quantity.description)
                    .font(.largeTitle)
                    .frame(maxWidth:100)
            } else {
                Text(" ")
                    .font(.largeTitle)
                    .frame(maxWidth:100)
            }
            Text(name)
                .font(.body)
            Spacer()
            Text(estimatedFormattedTimeString)
                .font(.body)
                .frame(maxWidth:50)
        }
        .padding()
        .background(backgroundColor)
    }
    
    private func backgroundColor() -> Color {
        if isExecuting {
            return AppEnvironment.current.theme.secondaryColor
        } else if hasExecuted {
            return AppEnvironment.current.theme.shadowColor
        } else {
            return AppEnvironment.current.theme.surfaceColor
        }
    }
}

struct StartWorkoutRowView_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing:0){
            StartWorkoutRowView(
                quantity: 0,
                name: "Test",
                estimatedFormattedTimeString: "00:00",
                isExecuting: true,
                hasExecuted: false
            )
            StartWorkoutRowView(
                quantity: 2,
                name: "Test2",
                estimatedFormattedTimeString: "02:00",
                isExecuting: false,
                hasExecuted: true
            )
        }
    }
}


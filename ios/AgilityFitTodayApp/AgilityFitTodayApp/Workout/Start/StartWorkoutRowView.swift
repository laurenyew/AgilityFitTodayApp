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
    
    var body: some View {
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
        .background(AppEnvironment.current.theme.surfaceColor)
    }
}

struct StartWorkoutRowView_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing:0){
            StartWorkoutRowView(
                quantity: 0,
                name: "Test",
                estimatedFormattedTimeString: "00:00"
            )
            StartWorkoutRowView(
                quantity: 2,
                name: "Test2",
                estimatedFormattedTimeString: "02:00"
            )
        }
    }
}


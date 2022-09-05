//
//  StartWorkoutHeaderView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 9/4/22.
//

import Foundation
import SwiftUI

/// Start Workout Sequence -- Header
struct StartWorkoutHeaderView : View {
    let title: String
    let description: String
    let estimatedTimeLeftString: String
    // TODO: Change image to match whatever comes from Workout Sequence
    
    var body: some View {
        VStack(alignment:.leading){
            HStack(alignment:.center) {
                Image("figure.run.circle.fill")
                    .symbolRenderingMode(.monochrome)
                    .resizable()
                    .frame(width: 40, height: 40, alignment: Alignment.center)
                    .scaledToFit()
                    .padding(.init(top: 10, leading: 0, bottom: 10, trailing: 10))
                VStack(alignment:.leading){
                    Text(title)
                        .font(.headline)
                    Text(description)
                        .font(.body)
                }
                Spacer()
            }
            .padding(.leading)
            
            HStack(alignment:.center) {
                Text("Estimated Time Left:")
                    .font(.body)
                    .bold()
                Text(estimatedTimeLeftString)
                    .font(.body)
                Spacer()
            }
            .padding(.leading)
        }
        .padding()
        .background(cardColor)
    }
}

struct StartWorkoutHeaderView_Previews: PreviewProvider {
    static var previews: some View {
        StartWorkoutHeaderView(
            title: "Cardio Treadmill",
            description: "Get ready to interval treadmill",
            estimatedTimeLeftString: "32:00"
        )
    }
}
